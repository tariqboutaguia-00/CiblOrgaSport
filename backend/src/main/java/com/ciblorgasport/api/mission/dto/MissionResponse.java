package com.ciblorgasport.api.mission.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MissionResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate missionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private List<String> volunteers;

    public MissionResponse(Long id, String title, String description,
                           LocalDate missionDate, LocalTime startTime,
                           LocalTime endTime, String location, List<String> volunteers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.missionDate = missionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.volunteers = volunteers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getMissionDate() {
        return missionDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getVolunteers() {
        return volunteers;
    }
}