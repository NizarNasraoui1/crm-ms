package contact_management.service.impl;

import contact_management.Util.KafkaUtil;
import contact_management.Util.PaginationAndFilteringUtil;
import contact_management.dto.ContactsByIdRequest;
import contact_management.dto.commons.FilteredPageWrapper;
import contact_management.dto.commons.SearchConfiguration;
import contact_management.dto.commons.SearchFields;
import contact_management.entity.Contact;
import contact_management.event.CrmBaseEntityCreatedEvent;
import contact_management.mapper.ContactMapper;
import contact_management.service.ContactService;
import contact_management.dto.ContactDto;
import contact_management.dto.DynamicSearchDto;
import contact_management.dto.ParamDto;
import contact_management.enumeration.ContactSearchFields;
import contact_management.enumeration.ContactSortFields;
import contact_management.repository.ContactRepository;
import contact_management.repository.CrmBaseEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactServiceImpl extends CrmBaseEntityServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final CrmBaseEntityRepository crmBaseEntityRepository;
    private final ContactMapper contactMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final KafkaUtil kafkaUtil;

    public ContactServiceImpl(ContactRepository contactRepository, CrmBaseEntityRepository crmBaseEntityRepository, ContactMapper contactMapper, ApplicationEventPublisher applicationEventPublisher, KafkaUtil kafkaUtil) {
        this.contactRepository = contactRepository;
        this.crmBaseEntityRepository = crmBaseEntityRepository;
        this.contactMapper = contactMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.kafkaUtil = kafkaUtil;
    }

    @Override
    public ContactDto findContactById(Long id) {
        return contactMapper.toDto((Contact) contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException("contact not found")));
    }

    @Override
    public ContactDto saveContact(ContactDto contactDto) {
        Contact newContact= contactRepository.save(contactMapper.toBo(contactDto));
        applicationEventPublisher.publishEvent(new CrmBaseEntityCreatedEvent(newContact));
        contactRepository.countContacts();
        return contactMapper.toDto(newContact);
    }

    @Override
    public ContactDto updateContactDetails(Long id, ContactDto contactDto) {
        Contact contact= (Contact) contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setAddress(contactDto.getAddress());
        contact.setEmail(contactDto.getEmail());
        return contactMapper.toDto(contact);
    }

    public Contact updateContact(Long id, ContactDto contactDto){
        Contact contact= (Contact) contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setAddress(contactDto.getAddress());
        contact.setEmail(contactDto.getEmail());
        return contact;
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        Contact contact=contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        contact.getOpportunities().forEach((opportunity -> opportunity.getContacts().remove(contact)));
        contactRepository.deleteById(id);
        contactRepository.countContacts();
        publishContactDeletedEvent(id);

    }


    @Override
    public SearchConfiguration getSearchParams() {
        SearchConfiguration searchConfiguration=new SearchConfiguration();
        for(ContactSortFields sortField: ContactSortFields.values()){
            searchConfiguration.getSortFields().add(new ParamDto(sortField.getName(),sortField.getLabel()));
        }
        for(ContactSearchFields searchField: ContactSearchFields.values()){
            searchConfiguration.getSearchFields().add(new ParamDto(searchField.getName(),searchField.getLabel()));
        }
        return searchConfiguration;
    }

    public FilteredPageWrapper<ContactDto> getContactFilteredPage(String searchWord, SearchFields searchFields, int page, int pageSize, String sortField, String sortDirection) {
        PageRequest pageRequest= PaginationAndFilteringUtil.getPaginationRequest(page,pageSize,sortField,sortDirection);
        List<ContactDto>contactDtoList=new ArrayList<>();
        Page<Contact> resultPage = null;
        int totalResults=0;
        if(!searchFields.getSearchFields().isEmpty() && !searchWord.isEmpty()) {
            List<Specification<Contact>> contactSpecificationList = new ArrayList<>();
            for (String searchField : searchFields.getSearchFields()) {
                contactSpecificationList.add((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(searchField), "%"+searchWord+"%"));
            };
            Specification<Contact> contactSpecification = contactSpecificationList.stream().reduce(Specification::or).orElse(null);
            resultPage = contactRepository.findAll(contactSpecification, pageRequest);
        }
        else{
            resultPage=contactRepository.findAll(pageRequest);
        }
        if(resultPage!=null){
            contactDtoList=resultPage.getContent().stream().map(contact->contactMapper.toDto((Contact) contact)).collect(Collectors.toList());
            totalResults=resultPage.getTotalPages()*pageSize;
        }
        return new FilteredPageWrapper<>(totalResults,contactDtoList);

    }

    @Override
    public Boolean hasOpportunity(Long id) {
        Contact contact=contactRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("contact not found"));
        if(contact.getOpportunities().size()!=0) return true;
        return false;
    }

    @Override
    public List<DynamicSearchDto> findContactDynamically(String searchWordParam) {
        Specification<Contact>contactFirstNameSpecification=(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"),"%"+searchWordParam)));
        Specification<Contact>contactFirstLastNameSpecification=(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("lastName"),"%"+searchWordParam)));
        Specification<Contact>contactGlobalSpecification=contactFirstNameSpecification.or(contactFirstLastNameSpecification);
        List<Contact>result=contactRepository.findAll(contactGlobalSpecification);
        return result.stream().map((contact -> {
            StringJoiner joiner=new StringJoiner(" ");
            if(contact.getFirstName()!=null && contact.getLastName()!=null){
                joiner.add(contact.getFirstName());
                joiner.add(contact.getLastName());
            }
            else if (contact.getFirstName()!=null && contact.getLastName()==null){
                joiner.add(contact.getFirstName());
            }
            else{
                joiner.add(contact.getLastName());
            }
            return new DynamicSearchDto(contact.getId(),joiner.toString());
        })).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "countContact")
    public int countContacts() {
        return contactRepository.countContacts();
    }

    @Override
    public List<ContactDto> findContactsByIds(ContactsByIdRequest contactsByIdRequest) {
        return contactMapper.toDtos(contactRepository.findAllByIdIn(contactsByIdRequest.getContactIds()));
    }

    @Override
    public void publishContactDeletedEvent(Long id) {
        kafkaUtil.sendMessage("contact-deleted",String.valueOf(id));
    }


}
