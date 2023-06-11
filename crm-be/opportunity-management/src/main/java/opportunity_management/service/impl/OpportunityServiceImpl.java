package opportunity_management.service.impl;

import lombok.extern.slf4j.Slf4j;
import opportunity_management.dto.ContactDto;
import opportunity_management.dto.ContactsByIdRequest;
import opportunity_management.dto.OpportunityDto;
import opportunity_management.entity.Opportunity;
import opportunity_management.enumeration.OpportunityStageEnum;
import opportunity_management.mapper.OpportunityMapper;
import opportunity_management.proxy.ContactProxy;
import opportunity_management.repository.OpportunityRepository;
import opportunity_management.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpportunityServiceImpl implements OpportunityService {
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OpportunityMapper opportunityMapper;

    @Autowired
    private ContactProxy contactProxy;

    @Override
    @Transactional
    public OpportunityDto saveNewOpportunity(OpportunityDto opportunityDto) {
        Opportunity opportunity=opportunityMapper.toBo(opportunityDto);
        if(opportunityDto.getStage()==null){
            opportunity.setStage(OpportunityStageEnum.FIRST_CONTACT);
        }
        opportunityDto.getContacts().stream().map(ContactDto::getId).forEach(id->opportunity.getContactIds().add(id));
        opportunityRepository.save(opportunity);
        return opportunityDto;
    }

    @Override
    public List<OpportunityDto> getAllOpportunities() {
        List<OpportunityDto>opportunityDtos=new ArrayList<>();
        List<Opportunity>opportunities=opportunityRepository.findAll();
        Set<Long> contactsIdsSet= new HashSet<>();
        for(Opportunity opportunity:opportunities){
            for(Long id:opportunity.getContactIds()){
                contactsIdsSet.add(id);
            }
        }
        ContactsByIdRequest contactsByIdRequest=new ContactsByIdRequest();
        contactsByIdRequest.setContactIds(new ArrayList<>(contactsIdsSet));
        List<ContactDto>ContactDtos=contactProxy.getAllContactsIn(contactsByIdRequest);
        Map<Long,ContactDto>contactDtoMap=ContactDtos.stream().collect(Collectors.toMap(ContactDto::getId,e->e));
        for(Opportunity opportunity:opportunities){
            OpportunityDto opportunityDto= opportunityMapper.toDto(opportunity);
            opportunity.getContactIds().stream().forEach((id)->opportunityDto.getContacts().add(contactDtoMap.get(id)));
            opportunityDtos.add(opportunityDto);
        }



        return opportunityDtos;
    }

//    @Override
//    public List<OpportunityDto> getAllOpportunitiesByStage(OpportunityStageEnum stage) {
//        return opportunityMapper.toDtos(opportunityRepository.findAllByStage(stage));
//    }

    @Override
    public List<ContactDto> getOpportunityContacts(Long id) {
        return null;
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
        for(ContactDto contacts:opportunityDto.getContacts()){
            opportunity.getContactIds().add(contacts.getId());
        }
        opportunityRepository.save(opportunity);
        return opportunityDto;
    }

    @Override
    public List<OpportunityDto> updateOpportunities(List<OpportunityDto> opportunityDtos) {
        List<Opportunity>opportunityList=opportunityRepository.findAllByIdIn(opportunityDtos.stream().map(OpportunityDto::getId).collect(Collectors.toList()));
        opportunityList.forEach((opportunity -> {
            OpportunityDto opportunityDto=opportunityDtos.stream().filter(e -> e.getId() == opportunity.getId()).findFirst().get();
            opportunity.setContactIds(opportunityDto.getContacts().stream().map(ContactDto::getId).collect(Collectors.toList()));
            opportunity.setStage(opportunityDto.getStage());
            opportunity.setName(opportunityDto.getName());
            opportunity.setCloseDate(opportunityDto.getCloseDate());
        }));
        opportunityRepository.saveAll(opportunityList);
        return opportunityMapper.toDtos(opportunityList);
    }

    @Override
    @Cacheable(value = "countOpportunities")
    public int countOpportunities() {
        return opportunityRepository.countOpportunities();
    }

    @Override
    public void consumeDeletedContact(Long id) {
        log.info(String.valueOf(id));
    }
}
