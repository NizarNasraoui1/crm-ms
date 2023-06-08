package contact_management.service.impl;

import contact_management.dto.ContactDto;
import contact_management.dto.OpportunityDto;
import contact_management.entity.Contact;
import contact_management.entity.Opportunity;
import contact_management.enumeration.OpportunityStageEnum;
import contact_management.event.CrmBaseEntityCreatedEvent;
import contact_management.mapper.ContactMapper;
import contact_management.mapper.OpportunityMapper;
import contact_management.repository.ContactRepository;
import contact_management.repository.OpportunityRepository;
import contact_management.service.ContactService;
import contact_management.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpportunityServiceImpl implements OpportunityService {
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OpportunityMapper opportunityMapper;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public OpportunityDto saveNewOpportunity(OpportunityDto opportunityDto) {
        Opportunity opportunity=opportunityMapper.toBo(opportunityDto);
        opportunity.setStage(OpportunityStageEnum.FIRST_CONTACT);
        List<Long>contactIds=opportunity.getContacts().stream().map(Contact::getId).collect(Collectors.toList());
        List<Contact>contactList=contactRepository.findAllByIdIn(contactIds);
        for (Contact contact : contactList) {
            contact.getOpportunities().add(opportunity);
        }
        contactRepository.saveAll(contactList);
        applicationEventPublisher.publishEvent(new CrmBaseEntityCreatedEvent(opportunity));
        opportunityRepository.countOpportunities();

        return opportunityMapper.toDto(opportunityRepository.save(opportunity));
    }

    @Override
    public List<OpportunityDto> getAllOpportunities() {
        return opportunityMapper.toDtos(opportunityRepository.findAll());
    }

    @Override
    public List<OpportunityDto> getAllOpportunitiesByStage(OpportunityStageEnum stage) {
        return opportunityMapper.toDtos(opportunityRepository.findAllByStage(stage));
    }

    @Override
    public List<ContactDto> getOpportunityContacts(Long id) {
        Opportunity opportunity= (Opportunity) opportunityRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        return contactMapper.toDtos(opportunity.getContacts());
    }

    @Override
    public void deleteOpportunity(Long id) {
        opportunityRepository.delete(opportunityRepository.findById(id).orElseThrow(()->new EntityNotFoundException("opportunity not found")));
        opportunityRepository.countOpportunities();

    }

    @Override
    public OpportunityDto updateOpportunity(Long id,OpportunityDto opportunityDto) {
        Opportunity opportunity=opportunityRepository.findById(id).orElseThrow(()->new EntityNotFoundException("opportunity not found"));
        opportunity.setName(opportunityDto.getName());
        opportunity.setStage(opportunityDto.getStage());
        List<Contact>contacts=opportunityDto.getContacts().stream().map((contact)->contactMapper.toBo(contact)).map((contact)->contactRepository.save(contact)).collect(Collectors.toList());
        opportunity.setContacts(contacts);
        return opportunityMapper.toDto(opportunityRepository.save(opportunity));
    }

    @Override
    public List<OpportunityDto> updateOpportunities(List<OpportunityDto> opportunityDtos) {
        opportunityDtos.forEach((opportunityDto -> {
            Opportunity opportunity=opportunityRepository.findById(opportunityDto.getId()).orElseThrow(()->new EntityNotFoundException("opportunity not found"));
            opportunity.setContacts(contactMapper.toBos(opportunityDto.getContacts()));
            opportunity.setStage(opportunityDto.getStage());
            opportunity.setName(opportunityDto.getName());
            opportunity.setCloseDate(opportunityDto.getCloseDate());
            opportunityRepository.save(opportunity);
        }));
        return opportunityMapper.toDtos(opportunityRepository.findAll());
    }

    @Override
    @Cacheable(value = "countOpportunities")
    public int countOpportunities() {
        return opportunityRepository.countOpportunities();
    }
}
