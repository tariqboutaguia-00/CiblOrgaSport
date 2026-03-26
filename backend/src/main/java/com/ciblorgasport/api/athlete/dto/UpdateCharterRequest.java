package com.ciblorgasport.api.athlete.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCharterRequest {

    @NotNull
    private Boolean charterAccepted;

    public UpdateCharterRequest() {
    }

    public Boolean getCharterAccepted() {
        return charterAccepted;
    }

    public void setCharterAccepted(Boolean charterAccepted) {
        this.charterAccepted = charterAccepted;
    }
}