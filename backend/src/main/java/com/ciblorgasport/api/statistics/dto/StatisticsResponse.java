package com.ciblorgasport.api.statistics.dto;

public class StatisticsResponse {

    private long totalUsers;
    private long totalCompetitions;
    private long totalEvents;
    private long totalAthletes;
    private long totalParticipants;
    private long totalResults;
    private long totalIncidents;
    private long totalNotifications;
    private long totalVolunteers;
    private long totalMissions;

    public StatisticsResponse(long totalUsers, long totalCompetitions, long totalEvents,
            long totalAthletes, long totalParticipants, long totalResults,
            long totalIncidents, long totalNotifications,
            long totalVolunteers, long totalMissions) {
        this.totalUsers = totalUsers;
        this.totalCompetitions = totalCompetitions;
        this.totalEvents = totalEvents;
        this.totalAthletes = totalAthletes;
        this.totalParticipants = totalParticipants;
        this.totalResults = totalResults;
        this.totalIncidents = totalIncidents;
        this.totalNotifications = totalNotifications;
        this.totalVolunteers = totalVolunteers;
        this.totalMissions = totalMissions;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalCompetitions() {
        return totalCompetitions;
    }

    public long getTotalEvents() {
        return totalEvents;
    }

    public long getTotalAthletes() {
        return totalAthletes;
    }

    public long getTotalParticipants() {
        return totalParticipants;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public long getTotalIncidents() {
        return totalIncidents;
    }

    public long getTotalNotifications() {
        return totalNotifications;
    }

    public long getTotalVolunteers() {
        return totalVolunteers;
    }

    public long getTotalMissions() {
        return totalMissions;
    }
}