package com.ciblorgasport.api.event.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventResponse {

    private Long id;
    private Long competitionId;
    private String competitionName;
    private String name;
    private LocalDate eventDate;
    private LocalTime startTime;
    private String location;
    private String status;
    private String athleteMeetingPoint;
    private String commissionerMeetingPoint;
    private String volunteerMeetingPoint;
    private String publicMeetingPoint;

    public EventResponse(Long id, Long competitionId, String competitionName, String name,
            LocalDate eventDate, LocalTime startTime, String location, String status,
            String athleteMeetingPoint, String commissionerMeetingPoint,
            String volunteerMeetingPoint, String publicMeetingPoint) {
        this.id = id;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.name = name;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.location = location;
        this.status = status;
        this.athleteMeetingPoint = athleteMeetingPoint;
        this.commissionerMeetingPoint = commissionerMeetingPoint;
        this.volunteerMeetingPoint = volunteerMeetingPoint;
        this.publicMeetingPoint = publicMeetingPoint;
    }

    public Long getId() {
        return id;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getName() {
        return name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getAthleteMeetingPoint() {
        return athleteMeetingPoint;
    }

    public String getCommissionerMeetingPoint() {
        return commissionerMeetingPoint;
    }

    public String getVolunteerMeetingPoint() {
        return volunteerMeetingPoint;
    }

    public String getPublicMeetingPoint() {
        return publicMeetingPoint;
    }
}