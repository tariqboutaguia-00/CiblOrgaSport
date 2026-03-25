package com.ciblorgasport.api.result.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateResultRequest {

    @NotNull
    private Long participantId;

    @NotNull
    @Positive
    private Integer rankPosition;

    @NotNull
    @PositiveOrZero
    private Double resultTime;

    private String medal;

    @NotNull
    private Boolean validated;

    public CreateResultRequest() {
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Integer getRankPosition() {
        return rankPosition;
    }

    public void setRankPosition(Integer rankPosition) {
        this.rankPosition = rankPosition;
    }

    public Double getResultTime() {
        return resultTime;
    }

    public void setResultTime(Double resultTime) {
        this.resultTime = resultTime;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}