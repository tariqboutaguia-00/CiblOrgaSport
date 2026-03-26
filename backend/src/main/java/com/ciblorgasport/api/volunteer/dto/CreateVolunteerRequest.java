package com.ciblorgasport.api.volunteer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateVolunteerRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 100)
    private String cenNumber;

    @NotBlank
    @Size(max = 150)
    private String federation;

    @NotBlank
    @Size(max = 50)
    private String accreditationStatus;

    public CreateVolunteerRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCenNumber() {
        return cenNumber;
    }

    public void setCenNumber(String cenNumber) {
        this.cenNumber = cenNumber;
    }

    public String getFederation() {
        return federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public String getAccreditationStatus() {
        return accreditationStatus;
    }

    public void setAccreditationStatus(String accreditationStatus) {
        this.accreditationStatus = accreditationStatus;
    }
}