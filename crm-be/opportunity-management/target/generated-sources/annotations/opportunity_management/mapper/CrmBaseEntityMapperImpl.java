package opportunity_management.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import opportunity_management.dto.CrmBaseEntityDto;
import opportunity_management.entity.CrmBaseEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T00:50:36+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class CrmBaseEntityMapperImpl implements CrmBaseEntityMapper {

    @Override
    public List<CrmBaseEntityDto> toDtos(List<CrmBaseEntity> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<CrmBaseEntityDto> list = new ArrayList<CrmBaseEntityDto>( dtos.size() );
        for ( CrmBaseEntity crmBaseEntity : dtos ) {
            list.add( toDto( crmBaseEntity ) );
        }

        return list;
    }

    @Override
    public List<CrmBaseEntity> toBos(List<CrmBaseEntityDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<CrmBaseEntity> list = new ArrayList<CrmBaseEntity>( bos.size() );
        for ( CrmBaseEntityDto crmBaseEntityDto : bos ) {
            list.add( toBo( crmBaseEntityDto ) );
        }

        return list;
    }

    @Override
    public CrmBaseEntityDto toDto(CrmBaseEntity bo) {
        if ( bo == null ) {
            return null;
        }

        CrmBaseEntityDto crmBaseEntityDto = new CrmBaseEntityDto();

        crmBaseEntityDto.setId( bo.getId() );
        crmBaseEntityDto.setCreateDate( bo.getCreateDate() );
        crmBaseEntityDto.setModifyDate( bo.getModifyDate() );

        return crmBaseEntityDto;
    }

    @Override
    public CrmBaseEntity toBo(CrmBaseEntityDto dto) {
        if ( dto == null ) {
            return null;
        }

        CrmBaseEntity crmBaseEntity = new CrmBaseEntity();

        crmBaseEntity.setId( dto.getId() );
        crmBaseEntity.setCreateDate( dto.getCreateDate() );
        crmBaseEntity.setModifyDate( dto.getModifyDate() );

        return crmBaseEntity;
    }

    @Override
    public CrmBaseEntity fillBo(CrmBaseEntityDto dto, CrmBaseEntity bo) {
        if ( dto == null ) {
            return null;
        }

        bo.setId( dto.getId() );
        bo.setCreateDate( dto.getCreateDate() );
        bo.setModifyDate( dto.getModifyDate() );

        return bo;
    }
}
