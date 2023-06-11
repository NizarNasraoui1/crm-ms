package opportunity_management.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "contact-deleted", groupId = "contact-management")
    public void listenTo(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }
}
