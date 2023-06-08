package opportunity_management.resource;

import opportunity_management.dto.NotificationDto;
import opportunity_management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/contact-management/notification")
public class NotificationResource {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/last")
    public ResponseEntity<List<NotificationDto>>getLastNotifications(){
        return new ResponseEntity<>(notificationService.getLastNotifications(), HttpStatus.OK);
    }
}
