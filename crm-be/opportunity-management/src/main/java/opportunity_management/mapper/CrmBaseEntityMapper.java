package opportunity_management.mapper;

import opportunity_management.dto.CrmBaseEntityDto;
import opportunity_management.entity.CrmBaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrmBaseEntityMapper extends GenericMapper<CrmBaseEntity, CrmBaseEntityDto> {
}
