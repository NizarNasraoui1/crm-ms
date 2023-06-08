package contact_management.service;

import contact_management.dto.CrmBaseEntityDto;
import contact_management.dto.NoteDto;
import contact_management.dto.commons.SearchConfiguration;

import java.util.List;

public interface CrmBaseEntityService{

    CrmBaseEntityDto getCrmBaseEntityById(Long id);

    public void deleteCrmBaseEntityById(Long id);

    SearchConfiguration getSearchParams();
    NoteDto getCrmBaseEntityNoteById(Long id);

    CrmBaseEntityDto addNoteToCrmBaseEntity(Long id,NoteDto noteDto);

    List<NoteDto> getCrmBaseEntityNotes(Long id);

}
