package com.ciblorgasport.api.notification.service;

import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.dto.NotificationResponse;
import com.ciblorgasport.api.notification.dto.SubscriptionResponse;
import com.ciblorgasport.api.notification.entity.Notification;
import com.ciblorgasport.api.notification.entity.Subscription;
import com.ciblorgasport.api.notification.repository.NotificationRepository;
import com.ciblorgasport.api.notification.repository.SubscriptionRepository;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository,
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public void createNotification(CreateNotificationRequest request) {
        List<Subscription> subscriptions = subscriptionRepository.findByType(request.getType());

        for (Subscription subscription : subscriptions) {
            Notification notification = new Notification();
            notification.setMessage(request.getMessage());
            notification.setType(request.getType());
            notification.setUser(subscription.getUser());
            notificationRepository.save(notification);
        }
    }

    public List<NotificationResponse> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToNotificationResponse)
                .toList();
    }

    public NotificationResponse markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        Notification savedNotification = notificationRepository.save(notification);

        return mapToNotificationResponse(savedNotification);
    }

    public SubscriptionResponse subscribe(Long userId, String type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (subscriptionRepository.existsByUserIdAndType(userId, type)) {
            throw new RuntimeException("Subscription already exists for this user and type");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setType(type);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToSubscriptionResponse(savedSubscription);
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getUser().getId(),
                notification.getUser().getEmail(),
                notification.getType(),
                notification.getMessage(),
                notification.isRead(),
                notification.getCreatedAt());
    }

    private SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getUser().getId(),
                subscription.getUser().getEmail(),
                subscription.getType());
    }
}