package opportunity_management.service.impl;

import opportunity_management.dto.NotificationDto;
import opportunity_management.mapper.NotificationMapper;
import opportunity_management.repository.NotificationRepository;
import opportunity_management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationMapper notificationMapper;
    @Override
    @Cacheable(value = "lastNotifications")
    public List<NotificationDto> getLastNotifications() {
        return notificationMapper.toDtos(notificationRepository.getLastNotifications());
    }
}
