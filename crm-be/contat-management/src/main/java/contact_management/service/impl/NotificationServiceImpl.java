package contact_management.service.impl;

import contact_management.dto.NotificationDto;
import contact_management.mapper.NotificationMapper;
import contact_management.repository.NotificationRepository;
import contact_management.service.NotificationService;
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
