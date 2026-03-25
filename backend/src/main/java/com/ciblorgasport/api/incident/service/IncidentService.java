package com.ciblorgasport.api.incident.service;

import com.ciblorgasport.api.event.entity.Event;
import com.ciblorgasport.api.event.repository.EventRepository;
import com.ciblorgasport.api.incident.dto.CreateIncidentRequest;
import com.ciblorgasport.api.incident.dto.IncidentResponse;
import com.ciblorgasport.api.incident.entity.Incident;
import com.ciblorgasport.api.incident.repository.IncidentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final EventRepository eventRepository;

    public IncidentService(IncidentRepository incidentRepository, EventRepository eventRepository) {
        this.incidentRepository = incidentRepository;
        this.eventRepository = eventRepository;
    }

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public IncidentResponse createIncident(CreateIncidentRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Incident incident = new Incident();
        incident.setEvent(event);
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setStatus(request.getStatus());
        incident.setImpactLevel(request.getImpactLevel());

        Incident savedIncident = incidentRepository.save(incident);
        return mapToResponse(savedIncident);
    }

    private IncidentResponse mapToResponse(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getEvent().getId(),
                incident.getEvent().getName(),
                incident.getTitle(),
                incident.getDescription(),
                incident.getStatus(),
                incident.getImpactLevel());
    }
}