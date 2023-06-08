package contact_management.mapper;

import contact_management.dto.OpportunityDto;
import contact_management.entity.Opportunity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpportunityMapper extends GenericMapper<Opportunity, OpportunityDto> {
}
