package com.ciblorgasport.api.result.dto;

public class ResultResponse {

    private Long id;
    private Long participantId;
    private String athleteName;
    private String eventName;
    private Integer rankPosition;
    private Double resultTime;
    private String medal;
    private boolean validated;

    public ResultResponse(Long id, Long participantId, String athleteName, String eventName,
            Integer rankPosition, Double resultTime, String medal, boolean validated) {
        this.id = id;
        this.participantId = participantId;
        this.athleteName = athleteName;
        this.eventName = eventName;
        this.rankPosition = rankPosition;
        this.resultTime = resultTime;
        this.medal = medal;
        this.validated = validated;
    }

    public Long getId() {
        return id;
    }

    public Long getParticipantId() {
        return participantId;
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