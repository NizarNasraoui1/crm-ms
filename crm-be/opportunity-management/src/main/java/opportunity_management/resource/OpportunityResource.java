package opportunity_management.resource;

import opportunity_management.dto.ContactDto;
import opportunity_management.dto.OpportunityDto;
import opportunity_management.enumeration.OpportunityStageEnum;
import opportunity_management.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/contact-management/opportunity")
public class OpportunityResource {
    @Autowired
    OpportunityService opportunityService;

    @GetMapping("/all")
    public ResponseEntity<List<OpportunityDto>> getAllOpportunities(){
        return new ResponseEntity<>(opportunityService.getAllOpportunities(),HttpStatus.OK);
    }

    @GetMapping("/stage")
    public ResponseEntity<List<OpportunityDto>> getAllOpportunitiesByStage(@RequestParam(value = "stage",required = true) OpportunityStageEnum stage){
        return new ResponseEntity<>(opportunityService.getAllOpportunitiesByStage(stage),HttpStatus.OK);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<ContactDto>> getOpportunityContacts(@PathVariable("id")Long id){
        return new ResponseEntity<>(opportunityService.getOpportunityContacts(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OpportunityDto> saveOpportunity(@RequestBody OpportunityDto opportunityDto){
        return new ResponseEntity<>(opportunityService.saveNewOpportunity(opportunityDto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OpportunityDto>updateOpportunity(@PathVariable("id")Long id,@RequestBody OpportunityDto opportunityDto){
        return ResponseEntity.ok(opportunityService.updateOpportunity(id,opportunityDto));
    }

    @PutMapping("/all")
    public ResponseEntity<List<OpportunityDto>>updateAllOpportunities(@RequestBody List<OpportunityDto> opportunityDtos){
        return ResponseEntity.ok(opportunityService.updateOpportunities(opportunityDtos));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteOpportunity(@PathVariable("id")Long id){
            opportunityService.deleteOpportunity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
