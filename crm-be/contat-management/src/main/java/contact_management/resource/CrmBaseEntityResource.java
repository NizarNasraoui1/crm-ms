package contact_management.resource;

import contact_management.dto.CrmBaseEntityDto;
import contact_management.dto.NoteDto;
import contact_management.service.CrmBaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CrmBaseEntityResource {
    @Autowired
    CrmBaseEntityService crmBaseEntityService;

    @GetMapping("/{id}")
    public ResponseEntity<CrmBaseEntityDto>findById(@PathVariable Long id){
        return new ResponseEntity<>(crmBaseEntityService.getCrmBaseEntityById(id),HttpStatus.OK);
    }

    @PostMapping("/{id}/note")
    public ResponseEntity<CrmBaseEntityDto>saveNoteToCrmBaseEntity(@PathVariable("id")Long id,@RequestBody NoteDto noteDto){
        return new ResponseEntity<>(crmBaseEntityService.addNoteToCrmBaseEntity(id,noteDto),HttpStatus.OK);
    }

    @GetMapping("/{id}/note")
    public ResponseEntity<List<NoteDto>>getCrmBaseEntityNotes(@PathVariable("id")Long id){
        return new ResponseEntity<>(crmBaseEntityService.getCrmBaseEntityNotes(id),HttpStatus.OK);
    }


}
