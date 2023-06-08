package opportunity_management.service;

import opportunity_management.dto.CrmBaseEntityDto;
import opportunity_management.dto.NoteDto;
import opportunity_management.dto.commons.SearchConfiguration;

import java.util.List;

public interface CrmBaseEntityService{

    CrmBaseEntityDto getCrmBaseEntityById(Long id);

    public void deleteCrmBaseEntityById(Long id);

    SearchConfiguration getSearchParams();
    NoteDto getCrmBaseEntityNoteById(Long id);

    CrmBaseEntityDto addNoteToCrmBaseEntity(Long id,NoteDto noteDto);

    List<NoteDto> getCrmBaseEntityNotes(Long id);

}
