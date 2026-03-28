package com.ciblorgasport.api.event.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEventRequest {

    @NotNull
    private Long competitionId;

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    @FutureOrPresent
    private LocalDate eventDate;

    @NotNull
    private LocalTime startTime;

    @NotBlank
    @Size(max = 150)
    private String location;

    @NotBlank
    @Size(max = 50)
    private String status;

    private String athleteMeetingPoint;
    private String commissionerMeetingPoint;
    private String volunteerMeetingPoint;
    private String publicMeetingPoint;

    public CreateEventRequest() {
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAthleteMeetingPoint() {
        return athleteMeetingPoint;
    }

    public void setAthleteMeetingPoint(String athleteMeetingPoint) {
        this.athleteMeetingPoint = athleteMeetingPoint;
    }

    public String getCommissionerMeetingPoint() {
        return commissionerMeetingPoint;
    }

    public void setCommissionerMeetingPoint(String commissionerMeetingPoint) {
        this.commissionerMeetingPoint = commissionerMeetingPoint;
    }

    public String getVolunteerMeetingPoint() {
        return volunteerMeetingPoint;
    }

    public void setVolunteerMeetingPoint(String volunteerMeetingPoint) {
        this.volunteerMeetingPoint = volunteerMeetingPoint;
    }

    public String getPublicMeetingPoint() {
        return publicMeetingPoint;
    }

    public void setPublicMeetingPoint(String publicMeetingPoint) {
        this.publicMeetingPoint = publicMeetingPoint;
    }
}