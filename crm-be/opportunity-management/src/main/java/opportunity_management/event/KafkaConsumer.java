package opportunity_management.event;

import opportunity_management.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    OpportunityService opportunityService;
    @KafkaListener(topics = "contact-deleted", groupId = "contact-management")
    public void hundleContactDeleted(String id) {

        opportunityService.deleteContactFromOpportunities(Long.parseLong(id));
    }
}