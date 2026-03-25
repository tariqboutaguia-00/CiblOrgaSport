package com.ciblorgasport.api.athlete.dto;

import java.time.LocalDate;

public class AthleteResponse {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String nationality;
    private LocalDate passportExpiration;
    private LocalDate medicalCertificateExpiration;
    private boolean charterAccepted;

    public AthleteResponse(Long id, Long userId, String firstName, String lastName, String email,
            String nationality, LocalDate passportExpiration,
            LocalDate medicalCertificateExpiration, boolean charterAccepted) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
        this.passportExpiration = passportExpiration;
        this.medicalCertificateExpiration = medicalCertificateExpiration;
        this.charterAccepted = charterAccepted;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getPassportExpiration() {
        return passportExpiration;
    }

    public LocalDate getMedicalCertificateExpiration() {
        return medicalCertificateExpiration;
    }

    public boolean isCharterAccepted() {
        return charterAccepted;
    }
}