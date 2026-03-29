package com.ciblorgasport.api.event.service;

import com.ciblorgasport.api.competition.entity.Competition;
import com.ciblorgasport.api.competition.repository.CompetitionRepository;
import com.ciblorgasport.api.event.dto.CreateEventRequest;
import com.ciblorgasport.api.event.dto.EventResponse;
import com.ciblorgasport.api.event.dto.RescheduleEventRequest;
import com.ciblorgasport.api.event.dto.UpdateMeetingPointsRequest;
import com.ciblorgasport.api.event.entity.Event;
import com.ciblorgasport.api.event.repository.EventRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CompetitionRepository competitionRepository;

    public EventService(EventRepository eventRepository, CompetitionRepository competitionRepository) {
        this.eventRepository = eventRepository;
        this.competitionRepository = competitionRepository;
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EventResponse createEvent(CreateEventRequest request) {
        Competition competition = competitionRepository.findById(request.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        Event event = new Event();
        event.setCompetition(competition);
        event.setName(request.getName());
        event.setEventDate(request.getEventDate());
        event.setStartTime(request.getStartTime());
        event.setLocation(request.getLocation());
        event.setStatus(request.getStatus());
        event.setAthleteMeetingPoint(request.getAthleteMeetingPoint());
        event.setCommissionerMeetingPoint(request.getCommissionerMeetingPoint());
        event.setVolunteerMeetingPoint(request.getVolunteerMeetingPoint());
        event.setPublicMeetingPoint(request.getPublicMeetingPoint());

        Event savedEvent = eventRepository.save(event);
        return mapToResponse(savedEvent);
    }

    public EventResponse cancelEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus("CANCELLED");
        Event savedEvent = eventRepository.save(event);

        return mapToResponse(savedEvent);
    }

    public EventResponse rescheduleEvent(Long eventId, RescheduleEventRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setEventDate(request.getEventDate());
        event.setStartTime(request.getStartTime());
        event.setStatus("RESCHEDULED");

        Event savedEvent = eventRepository.save(event);
        return mapToResponse(savedEvent);
    }

    public EventResponse updateMeetingPoints(Long eventId, UpdateMeetingPointsRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setAthleteMeetingPoint(request.getAthleteMeetingPoint());
        event.setCommissionerMeetingPoint(request.getCommissionerMeetingPoint());
        event.setVolunteerMeetingPoint(request.getVolunteerMeetingPoint());
        event.setPublicMeetingPoint(request.getPublicMeetingPoint());

        Event savedEvent = eventRepository.save(event);
        return mapToResponse(savedEvent);
    }

    public List<EventResponse> getPublicSchedule() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EventResponse mapToResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getCompetition().getId(),
                event.getCompetition().getName(),
                event.getName(),
                event.getEventDate(),
                event.getStartTime(),
                event.getLocation(),
                event.getStatus(),
                event.getAthleteMeetingPoint(),
                event.getCommissionerMeetingPoint(),
                event.getVolunteerMeetingPoint(),
                event.getPublicMeetingPoint());
    }
}