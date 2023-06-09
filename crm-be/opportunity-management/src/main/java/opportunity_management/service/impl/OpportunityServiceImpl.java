package opportunity_management.service.impl;

import opportunity_management.dto.ContactDto;
import opportunity_management.dto.OpportunityDto;
import opportunity_management.entity.Opportunity;
import opportunity_management.enumeration.OpportunityStageEnum;
import opportunity_management.mapper.OpportunityMapper;
import opportunity_management.proxy.ContactProxy;
import opportunity_management.repository.OpportunityRepository;
import opportunity_management.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
        opportunityDto.getContacts().stream().map(ContactDto::getId).forEach(id->opportunity.getContactIds().add(id));
        return opportunityMapper.toDto(opportunityRepository.save(opportunity));
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
        List<ContactDto>ContactDtos=contactProxy.getAllContactsIn(new ArrayList<>(contactsIdsSet));
        Map<Long,ContactDto>contactDtoMap=ContactDtos.stream().collect(Collectors.toMap(ContactDto::getId,e->e));
        for(Opportunity opportunity:opportunities){
            OpportunityDto opportunityDto= opportunityMapper.toDto(opportunity);
            opportunity.getContactIds().stream().forEach((id)->opportunityDto.getContacts().add(contactDtoMap.get(id)));
            opportunityDtos.add(opportunityDto);
        }



        return opportunityDtos;
    }

    @Override
    public List<OpportunityDto> getAllOpportunitiesByStage(OpportunityStageEnum stage) {
//        return opportunityMapper.toDtos(opportunityRepository.findAllByStage(stage));
        return null;
    }

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
//        Opportunity opportunity=opportunityRepository.findById(id).orElseThrow(()->new EntityNotFoundException("opportunity not found"));
//        opportunity.setName(opportunityDto.getName());
//        opportunity.setStage(opportunityDto.getStage());
//        List<Contact>contacts=opportunityDto.getContacts().stream().map((contact)->contactMapper.toBo(contact)).map((contact)->contactRepository.save(contact)).collect(Collectors.toList());
//        opportunity.setContacts(contacts);
        return null;
    }

    @Override
    public List<OpportunityDto> updateOpportunities(List<OpportunityDto> opportunityDtos) {
//        opportunityDtos.forEach((opportunityDto -> {
//            Opportunity opportunity=opportunityRepository.findById(opportunityDto.getId()).orElseThrow(()->new EntityNotFoundException("opportunity not found"));
//            opportunity.setContacts(contactMapper.toBos(opportunityDto.getContacts()));
//            opportunity.setStage(opportunityDto.getStage());
//            opportunity.setName(opportunityDto.getName());
//            opportunity.setCloseDate(opportunityDto.getCloseDate());
//            opportunityRepository.save(opportunity);
//        }));
        return null;
    }

    @Override
    @Cacheable(value = "countOpportunities")
    public int countOpportunities() {
        return opportunityRepository.countOpportunities();
    }
}
