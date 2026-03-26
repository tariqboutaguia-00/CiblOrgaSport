package com.ciblorgasport.api.result.dto;

public class AthletePerformanceResponse {

    private Long resultId;
    private Long athleteId;
    private String athleteName;
    private String eventName;
    private Integer rankPosition;
    private Double resultTime;
    private String medal;
    private boolean validated;

    public AthletePerformanceResponse(Long resultId, Long athleteId, String athleteName,
            String eventName, Integer rankPosition,
            Double resultTime, String medal, boolean validated) {
        this.resultId = resultId;
        this.athleteId = athleteId;
        this.athleteName = athleteName;
        this.eventName = eventName;
        this.rankPosition = rankPosition;
        this.resultTime = resultTime;
        this.medal = medal;
        this.validated = validated;
    }

    public Long getResultId() {
        return resultId;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getEventName() {
        return eventName;
    }

    public Integer getRankPosition() {
        return rankPosition;
    }

    public Double getResultTime() {
        return resultTime;
    }

    public String getMedal() {
        return medal;
    }

    public boolean isValidated() {
        return validated;
    }
}