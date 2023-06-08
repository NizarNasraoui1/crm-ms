package opportunity_management.service;

import opportunity_management.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getLastNotifications();
}
