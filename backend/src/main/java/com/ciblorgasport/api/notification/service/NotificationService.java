package com.ciblorgasport.api.notification.service;

import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.entity.Notification;
import com.ciblorgasport.api.notification.entity.Subscription;
import com.ciblorgasport.api.notification.repository.NotificationRepository;
import com.ciblorgasport.api.notification.repository.SubscriptionRepository;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        for (Subscription sub : subscriptions) {
            Notification n = new Notification();
            n.setMessage(request.getMessage());
            n.setType(request.getType());
            n.setUser(sub.getUser());
            notificationRepository.save(n);
        }
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Notification markAsRead(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        n.setRead(true);
        return notificationRepository.save(n);
    }

    public Subscription subscribe(Long userId, String type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subscription sub = new Subscription();
        sub.setUser(user);
        sub.setType(type);

        return subscriptionRepository.save(sub);
    }
}