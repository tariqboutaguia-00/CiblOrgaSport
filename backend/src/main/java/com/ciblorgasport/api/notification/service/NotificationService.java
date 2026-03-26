package com.ciblorgasport.api.notification.service;

import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.dto.NotificationResponse;
import com.ciblorgasport.api.notification.entity.Notification;
import com.ciblorgasport.api.notification.repository.NotificationRepository;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public NotificationResponse createNotification(CreateNotificationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(request.getType());
        notification.setMessage(request.getMessage());
        notification.setRead(request.getRead());

        Notification savedNotification = notificationRepository.save(notification);
        return mapToResponse(savedNotification);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getId(),
                notification.getUser().getEmail(),
                notification.getType(),
                notification.getMessage(),
                notification.isRead());
    }
}