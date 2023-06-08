package opportunity_management.repository;

import opportunity_management.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query(value = "SELECT * FROM Notification ORDER BY create_date desc  LIMIT 4",nativeQuery = true)
    List<Notification>getLastNotifications();
}
