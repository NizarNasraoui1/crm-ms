package opportunity_management.resource;

import opportunity_management.dto.StatisticsDto;
import opportunity_management.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contact-management/statistics")
public class StatisticsResource {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDto>getStatistics(){
        return new ResponseEntity<>(statisticsService.getStatistics(), HttpStatus.OK);
    }

}
