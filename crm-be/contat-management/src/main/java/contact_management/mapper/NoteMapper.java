package contact_management.mapper;

import contact_management.dto.NoteDto;
import contact_management.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper extends GenericMapper<Note, NoteDto> {

}
