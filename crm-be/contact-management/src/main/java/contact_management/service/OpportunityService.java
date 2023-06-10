package contact_management.service;

import contact_management.dto.ContactDto;
import contact_management.dto.OpportunityDto;
import contact_management.enumeration.OpportunityStageEnum;

import java.util.List;

public interface OpportunityService {
    OpportunityDto saveNewOpportunity(OpportunityDto opportunityDto);

    List<OpportunityDto>getAllOpportunities();

    List<OpportunityDto> getAllOpportunitiesByStage(OpportunityStageEnum stage);

    List<ContactDto> getOpportunityContacts(Long id);

    void deleteOpportunity(Long id);

    OpportunityDto updateOpportunity(Long id,OpportunityDto opportunityDto);

    List<OpportunityDto> updateOpportunities(List<OpportunityDto>opportunityDtos);

    int countOpportunities();
}
