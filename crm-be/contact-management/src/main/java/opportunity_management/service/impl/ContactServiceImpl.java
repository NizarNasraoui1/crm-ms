package opportunity_management.service.impl;

import opportunity_management.Util.PaginationAndFilteringUtil;
import opportunity_management.dto.ContactDto;
import opportunity_management.dto.DynamicSearchDto;
import opportunity_management.dto.ParamDto;
import opportunity_management.dto.commons.FilteredPageWrapper;
import opportunity_management.dto.commons.SearchConfiguration;
import opportunity_management.dto.commons.SearchFields;
import opportunity_management.entity.Contact;
import opportunity_management.enumeration.ContactSearchFields;
import opportunity_management.enumeration.ContactSortFields;
import opportunity_management.event.CrmBaseEntityCreatedEvent;
import opportunity_management.mapper.ContactMapper;
import opportunity_management.repository.ContactRepository;
import opportunity_management.repository.CrmBaseEntityRepository;
import opportunity_management.service.ContactService;
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
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactServiceImpl extends CrmBaseEntityServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CrmBaseEntityRepository crmBaseEntityRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


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
        Contact updateContact= (Contact) contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        updateContact.setFirstName(contactDto.getFirstName());
        updateContact.setLastName(contactDto.getLastName());
        updateContact.setAddress(contactDto.getAddress());
        updateContact.setEmail(contactDto.getEmail());
        return contactMapper.toDto(new Contact());
    }

    @Override
    @Transactional
    public void deleteContact(Long id) {
        Contact contact=contactRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        contact.getOpportunities().forEach((opportunity -> opportunity.getContacts().remove(contact)));
        contactRepository.deleteById(id);
        contactRepository.countContacts();
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
            String content;
            if(contact.getFirstName()!=null && contact.getLastName()!=null){
                content=contact.getFirstName()+" "+contact.getLastName();
            }
            else if (contact.getFirstName()!=null){
                content=contact.getFirstName()+" "+contact.getLastName();
            }
            else{
                content=contact.getFirstName()+" "+contact.getLastName();
            }
            return new DynamicSearchDto(contact.getId(),content);
        })).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "countContact")
    public int countContacts() {
        return contactRepository.countContacts();
    }



}
