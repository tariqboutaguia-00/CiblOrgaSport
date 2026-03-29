package com.ciblorgasport.api.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNotificationRequest {

    @NotBlank
    @Size(max = 50)
    private String type;

    @NotBlank
    private String message;

    public CreateNotificationRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}