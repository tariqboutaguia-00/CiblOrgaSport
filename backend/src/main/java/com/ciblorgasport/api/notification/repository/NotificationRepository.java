package com.ciblorgasport.api.notification.repository;

import com.ciblorgasport.api.notification.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}