package com.ciblorgasport.api.notification.dto;

public class NotificationResponse {

    private Long id;
    private Long userId;
    private String userEmail;
    private String type;
    private String message;
    private boolean read;

    public NotificationResponse(Long id, Long userId, String userEmail, String type, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.type = type;
        this.message = message;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }
}