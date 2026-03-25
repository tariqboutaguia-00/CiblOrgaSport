package com.ciblorgasport.api.athlete.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateAthleteRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String nationality;

    @NotNull
    @Future
    private LocalDate passportExpiration;

    @NotNull
    @Future
    private LocalDate medicalCertificateExpiration;

    @NotNull
    private Boolean charterAccepted;

    public CreateAthleteRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(LocalDate passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public LocalDate getMedicalCertificateExpiration() {
        return medicalCertificateExpiration;
    }

    public void setMedicalCertificateExpiration(LocalDate medicalCertificateExpiration) {
        this.medicalCertificateExpiration = medicalCertificateExpiration;
    }

    public Boolean getCharterAccepted() {
        return charterAccepted;
    }

    public void setCharterAccepted(Boolean charterAccepted) {
        this.charterAccepted = charterAccepted;
    }
}