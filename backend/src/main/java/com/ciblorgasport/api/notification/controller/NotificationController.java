package com.ciblorgasport.api.notification.controller;

import com.ciblorgasport.api.notification.dto.CreateNotificationRequest;
import com.ciblorgasport.api.notification.dto.NotificationResponse;
import com.ciblorgasport.api.notification.service.NotificationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponse createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        return notificationService.createNotification(request);
    }
}