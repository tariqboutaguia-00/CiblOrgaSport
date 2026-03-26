package com.ciblorgasport.api.volunteer.dto;

public class VolunteerResponse {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String cenNumber;
    private String federation;
    private String accreditationStatus;

    public VolunteerResponse(Long id, Long userId, String firstName, String lastName, String email,
            String cenNumber, String federation, String accreditationStatus) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cenNumber = cenNumber;
        this.federation = federation;
        this.accreditationStatus = accreditationStatus;
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

    public String getCenNumber() {
        return cenNumber;
    }

    public String getFederation() {
        return federation;
    }

    public String getAccreditationStatus() {
        return accreditationStatus;
    }
}