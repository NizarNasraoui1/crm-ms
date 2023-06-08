package opportunity_management.mapper;

import opportunity_management.dto.OpportunityDto;
import opportunity_management.entity.Opportunity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpportunityMapper extends GenericMapper<Opportunity, OpportunityDto> {
}
