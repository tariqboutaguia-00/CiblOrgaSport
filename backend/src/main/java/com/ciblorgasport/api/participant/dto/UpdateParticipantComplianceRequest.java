package com.ciblorgasport.api.participant.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateParticipantComplianceRequest {

    @NotNull
    private Boolean compliant;

    public UpdateParticipantComplianceRequest() {
    }

    public Boolean getCompliant() {
        return compliant;
    }

    public void setCompliant(Boolean compliant) {
        this.compliant = compliant;
    }
}