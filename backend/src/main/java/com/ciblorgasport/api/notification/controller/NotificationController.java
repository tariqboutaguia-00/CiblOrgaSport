package com.ciblorgasport.api.notification.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.dto.SubscriptionRequest;
import com.ciblorgasport.api.notification.entity.Notification;
import com.ciblorgasport.api.notification.entity.Subscription;
import com.ciblorgasport.api.notification.service.NotificationService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Void> create(@RequestBody CreateNotificationRequest request) {
        service.createNotification(request);
        return ApiResponse.success("Notification sent", null);
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Notification>> getUser(@PathVariable Long userId) {
        return ApiResponse.success("Notifications", service.getUserNotifications(userId));
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<Notification> read(@PathVariable Long id) {
        return ApiResponse.success("Updated", service.markAsRead(id));
    }

    @PostMapping("/subscribe")
    public ApiResponse<Subscription> subscribe(@RequestBody SubscriptionRequest request) {
        return ApiResponse.success("Subscribed", service.subscribe(request.getUserId(), request.getType()));
    }
}