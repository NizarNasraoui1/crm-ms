package opportunity_management.service;

import opportunity_management.dto.ContactDto;
import opportunity_management.dto.OpportunityDto;
import opportunity_management.enumeration.OpportunityStageEnum;

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
