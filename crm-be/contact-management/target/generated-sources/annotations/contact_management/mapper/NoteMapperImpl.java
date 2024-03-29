package contact_management.mapper;

import contact_management.dto.NoteDto;
import contact_management.entity.Note;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T21:32:56+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public List<NoteDto> toDtos(List<Note> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<NoteDto> list = new ArrayList<NoteDto>( dtos.size() );
        for ( Note note : dtos ) {
            list.add( toDto( note ) );
        }

        return list;
    }

    @Override
    public List<Note> toBos(List<NoteDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<Note> list = new ArrayList<Note>( bos.size() );
        for ( NoteDto noteDto : bos ) {
            list.add( toBo( noteDto ) );
        }

        return list;
    }

    @Override
    public NoteDto toDto(Note bo) {
        if ( bo == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( bo.getId() );
        noteDto.setTitle( bo.getTitle() );
        noteDto.setContent( bo.getContent() );

        return noteDto;
    }

    @Override
    public Note toBo(NoteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Note note = new Note();

        note.setId( dto.getId() );
        note.setTitle( dto.getTitle() );
        note.setContent( dto.getContent() );

        return note;
    }

    @Override
    public Note fillBo(NoteDto dto, Note bo) {
        if ( dto == null ) {
            return null;
        }

        bo.setId( dto.getId() );
        bo.setTitle( dto.getTitle() );
        bo.setContent( dto.getContent() );

        return bo;
    }
}
