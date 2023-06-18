package contact_management.mapper;

import contact_management.dto.CrmBaseEntityDto;
import contact_management.dto.NoteDto;
import contact_management.entity.CrmBaseEntity;
import contact_management.entity.Note;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-18T15:04:51+0200",
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
        crmBaseEntityDto.setNoteList( noteListToNoteDtoList( bo.getNoteList() ) );

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
        crmBaseEntity.setNoteList( noteDtoListToNoteList( dto.getNoteList() ) );

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
        if ( bo.getNoteList() != null ) {
            List<Note> list = noteDtoListToNoteList( dto.getNoteList() );
            if ( list != null ) {
                bo.getNoteList().clear();
                bo.getNoteList().addAll( list );
            }
            else {
                bo.setNoteList( null );
            }
        }
        else {
            List<Note> list = noteDtoListToNoteList( dto.getNoteList() );
            if ( list != null ) {
                bo.setNoteList( list );
            }
        }

        return bo;
    }

    protected NoteDto noteToNoteDto(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( note.getId() );
        noteDto.setTitle( note.getTitle() );
        noteDto.setContent( note.getContent() );

        return noteDto;
    }

    protected List<NoteDto> noteListToNoteDtoList(List<Note> list) {
        if ( list == null ) {
            return null;
        }

        List<NoteDto> list1 = new ArrayList<NoteDto>( list.size() );
        for ( Note note : list ) {
            list1.add( noteToNoteDto( note ) );
        }

        return list1;
    }

    protected Note noteDtoToNote(NoteDto noteDto) {
        if ( noteDto == null ) {
            return null;
        }

        Note note = new Note();

        note.setId( noteDto.getId() );
        note.setTitle( noteDto.getTitle() );
        note.setContent( noteDto.getContent() );

        return note;
    }

    protected List<Note> noteDtoListToNoteList(List<NoteDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Note> list1 = new ArrayList<Note>( list.size() );
        for ( NoteDto noteDto : list ) {
            list1.add( noteDtoToNote( noteDto ) );
        }

        return list1;
    }
}
