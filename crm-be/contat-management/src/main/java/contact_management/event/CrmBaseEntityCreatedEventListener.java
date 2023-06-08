package contact_management.event;

import contact_management.Util.CacheUtil;
import contact_management.entity.Contact;
import contact_management.entity.CrmBaseEntity;
import contact_management.entity.Notification;
import contact_management.entity.Opportunity;
import contact_management.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CrmBaseEntityCreatedEventListener implements ApplicationListener<CrmBaseEntityCreatedEvent> {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    CacheUtil cacheUtil;
    @Override
    @Async
    public void onApplicationEvent(CrmBaseEntityCreatedEvent crmBaseEntityCreatedEvent) {
        CrmBaseEntity crmBaseEntity=crmBaseEntityCreatedEvent.getCrmBaseEntity();
        if(crmBaseEntity instanceof Contact){
            Notification notification=Notification.builder().title("New Contact Created").message("New Contact Created with name: "+((Contact) crmBaseEntity).getFirstName()).build();
            notificationRepository.save(notification);
        }
        if(crmBaseEntity instanceof Opportunity){
            Notification notification=Notification.builder().title("New Opportunity Created").message("New Opportunity Created with name: "+((Opportunity) crmBaseEntity).getName()).build();
            notificationRepository.save(notification);
        }
        cacheUtil.evictAllCacheValues("lastNotifications");

    }
}
