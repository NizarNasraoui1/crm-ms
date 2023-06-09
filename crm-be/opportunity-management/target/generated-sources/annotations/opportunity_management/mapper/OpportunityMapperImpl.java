package opportunity_management.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import opportunity_management.dto.OpportunityDto;
import opportunity_management.entity.Opportunity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T18:29:39+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class OpportunityMapperImpl implements OpportunityMapper {

    @Override
    public List<OpportunityDto> toDtos(List<Opportunity> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OpportunityDto> list = new ArrayList<OpportunityDto>( dtos.size() );
        for ( Opportunity opportunity : dtos ) {
            list.add( toDto( opportunity ) );
        }

        return list;
    }

    @Override
    public List<Opportunity> toBos(List<OpportunityDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<Opportunity> list = new ArrayList<Opportunity>( bos.size() );
        for ( OpportunityDto opportunityDto : bos ) {
            list.add( toBo( opportunityDto ) );
        }

        return list;
    }

    @Override
    public OpportunityDto toDto(Opportunity bo) {
        if ( bo == null ) {
            return null;
        }

        OpportunityDto opportunityDto = new OpportunityDto();

        opportunityDto.setId( bo.getId() );
        opportunityDto.setCreateDate( bo.getCreateDate() );
        opportunityDto.setModifyDate( bo.getModifyDate() );
        opportunityDto.setName( bo.getName() );
        opportunityDto.setStage( bo.getStage() );
        opportunityDto.setCloseDate( bo.getCloseDate() );

        return opportunityDto;
    }

    @Override
    public Opportunity toBo(OpportunityDto dto) {
        if ( dto == null ) {
            return null;
        }

        Opportunity opportunity = new Opportunity();

        opportunity.setId( dto.getId() );
        opportunity.setCreateDate( dto.getCreateDate() );
        opportunity.setModifyDate( dto.getModifyDate() );
        opportunity.setName( dto.getName() );
        opportunity.setStage( dto.getStage() );
        opportunity.setCloseDate( dto.getCloseDate() );

        return opportunity;
    }

    @Override
    public Opportunity fillBo(OpportunityDto dto, Opportunity bo) {
        if ( dto == null ) {
            return null;
        }

        bo.setId( dto.getId() );
        bo.setCreateDate( dto.getCreateDate() );
        bo.setModifyDate( dto.getModifyDate() );
        bo.setName( dto.getName() );
        bo.setStage( dto.getStage() );
        bo.setCloseDate( dto.getCloseDate() );

        return bo;
    }
}
