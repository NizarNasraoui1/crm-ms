package contact_management.event;

import contact_management.entity.CrmBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter @Setter
public class CrmBaseEntityCreatedEvent extends ApplicationEvent {
    private CrmBaseEntity crmBaseEntity;
    public CrmBaseEntityCreatedEvent(CrmBaseEntity crmBaseEntity){
        super(crmBaseEntity);
        this.crmBaseEntity=crmBaseEntity;
    }
}
