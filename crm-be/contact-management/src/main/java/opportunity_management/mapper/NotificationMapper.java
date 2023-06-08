package opportunity_management.mapper;

import opportunity_management.dto.NotificationDto;
import opportunity_management.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends GenericMapper<Notification, NotificationDto> {
}
