package contact_management.service;

import contact_management.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getLastNotifications();
}
