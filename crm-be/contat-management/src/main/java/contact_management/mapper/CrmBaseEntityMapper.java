package contact_management.mapper;

import contact_management.dto.CrmBaseEntityDto;
import contact_management.entity.CrmBaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrmBaseEntityMapper extends GenericMapper<CrmBaseEntity, CrmBaseEntityDto> {
}
