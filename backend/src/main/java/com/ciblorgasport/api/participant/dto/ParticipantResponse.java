package com.ciblorgasport.api.participant.dto;

import java.time.LocalDateTime;

public class ParticipantResponse {

    private Long id;
    private Long athleteId;
    private String athleteName;
    private Long eventId;
    private String eventName;
    private String status;
    private boolean compliant;
    private LocalDateTime registeredAt;

    public ParticipantResponse(Long id, Long athleteId, String athleteName,
            Long eventId, String eventName, String status,
            boolean compliant, LocalDateTime registeredAt) {
        this.id = id;
        this.athleteId = athleteId;
        this.athleteName = athleteName;
        this.eventId = eventId;
        this.eventName = eventName;
        this.status = status;
        this.compliant = compliant;
        this.registeredAt = registeredAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
}