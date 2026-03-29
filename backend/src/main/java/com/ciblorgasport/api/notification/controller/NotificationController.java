package com.ciblorgasport.api.notification.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.dto.NotificationResponse;
import com.ciblorgasport.api.notification.dto.SubscriptionRequest;
import com.ciblorgasport.api.notification.dto.SubscriptionResponse;
import com.ciblorgasport.api.notification.service.NotificationService;
import com.ciblorgasport.api.user.entity.User;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Void> create(@Valid @RequestBody CreateNotificationRequest request) {
        service.createNotification(request);
        return ApiResponse.success("Notification sent successfully", null);
    }

    @GetMapping("/me")
    public ApiResponse<List<NotificationResponse>> getCurrentUserNotifications(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return ApiResponse.success(
                "Notifications retrieved successfully",
                service.getUserNotifications(currentUser.getId()));
    }

    @PatchMapping("/{id}/read")
    public ApiResponse<NotificationResponse> read(@PathVariable Long id) {
        return ApiResponse.success("Notification marked as read successfully", service.markAsRead(id));
    }

    @PostMapping("/subscribe")
    public ApiResponse<SubscriptionResponse> subscribe(@Valid @RequestBody SubscriptionRequest request) {
        return ApiResponse.success(
                "Subscription created successfully",
                service.subscribe(request.getUserId(), request.getType()));
    }
}