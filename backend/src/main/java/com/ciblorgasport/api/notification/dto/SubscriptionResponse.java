package com.ciblorgasport.api.notification.dto;

public class SubscriptionResponse {

    private Long id;
    private Long userId;
    private String userEmail;
    private String type;

    public SubscriptionResponse(Long id, Long userId, String userEmail, String type) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.type = type;
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
}