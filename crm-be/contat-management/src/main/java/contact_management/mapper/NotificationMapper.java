package contact_management.mapper;

import contact_management.dto.NotificationDto;
import contact_management.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends GenericMapper<Notification, NotificationDto> {
}
