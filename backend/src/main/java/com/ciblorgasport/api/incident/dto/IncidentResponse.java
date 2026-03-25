package com.ciblorgasport.api.incident.dto;

public class IncidentResponse {

    private Long id;
    private Long eventId;
    private String eventName;
    private String title;
    private String description;
    private String status;
    private String impactLevel;

    public IncidentResponse(Long id, Long eventId, String eventName, String title,
            String description, String status, String impactLevel) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.title = title;
        this.description = description;
        this.status = status;
        this.impactLevel = impactLevel;
    }

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getImpactLevel() {
        return impactLevel;
    }
}