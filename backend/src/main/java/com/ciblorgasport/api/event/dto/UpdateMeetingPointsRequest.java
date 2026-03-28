package com.ciblorgasport.api.event.dto;

public class UpdateMeetingPointsRequest {

    private String athleteMeetingPoint;
    private String commissionerMeetingPoint;
    private String volunteerMeetingPoint;
    private String publicMeetingPoint;

    public UpdateMeetingPointsRequest() {
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