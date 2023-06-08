package opportunity_management.mapper;

import opportunity_management.dto.NoteDto;
import opportunity_management.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper extends GenericMapper<Note, NoteDto> {

}
