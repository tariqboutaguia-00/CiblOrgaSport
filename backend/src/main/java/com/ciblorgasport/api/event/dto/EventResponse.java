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

    public EventResponse(Long id, Long competitionId, String competitionName, String name,
            LocalDate eventDate, LocalTime startTime, String location, String status) {
        this.id = id;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.name = name;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.location = location;
        this.status = status;
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
}