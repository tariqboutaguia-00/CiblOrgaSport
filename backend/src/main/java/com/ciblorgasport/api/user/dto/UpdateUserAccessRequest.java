package com.ciblorgasport.api.user.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateUserAccessRequest {

    @NotNull
    private Boolean enabled;

    public UpdateUserAccessRequest() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}