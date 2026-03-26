package com.ciblorgasport.api.participant.service;

import com.ciblorgasport.api.athlete.entity.Athlete;
import com.ciblorgasport.api.athlete.repository.AthleteRepository;
import com.ciblorgasport.api.event.entity.Event;
import com.ciblorgasport.api.event.repository.EventRepository;
import com.ciblorgasport.api.participant.dto.CreateParticipantRequest;
import com.ciblorgasport.api.participant.dto.ParticipantResponse;
import com.ciblorgasport.api.participant.entity.Participant;
import com.ciblorgasport.api.participant.repository.ParticipantRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final AthleteRepository athleteRepository;
    private final EventRepository eventRepository;

    public ParticipantService(
            ParticipantRepository participantRepository,
            AthleteRepository athleteRepository,
            EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.athleteRepository = athleteRepository;
        this.eventRepository = eventRepository;
    }

    public List<ParticipantResponse> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ParticipantResponse createParticipant(CreateParticipantRequest request) {
        Athlete athlete = athleteRepository.findById(request.getAthleteId())
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (participantRepository.existsByAthleteIdAndEventId(athlete.getId(), event.getId())) {
            throw new RuntimeException("Athlete is already registered for this event");
        }

        Participant participant = new Participant();
        participant.setAthlete(athlete);
        participant.setEvent(event);
        participant.setStatus(request.getStatus());

        Participant savedParticipant = participantRepository.save(participant);
        return mapToResponse(savedParticipant);
    }

    public ParticipantResponse withdrawParticipant(Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        participant.setStatus("WITHDRAWN");
        Participant savedParticipant = participantRepository.save(participant);

        return mapToResponse(savedParticipant);
    }

    public ParticipantResponse updateCompliance(Long participantId, boolean compliant) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        participant.setCompliant(compliant);
        Participant savedParticipant = participantRepository.save(participant);

        return mapToResponse(savedParticipant);
    }

    private ParticipantResponse mapToResponse(Participant participant) {
        String athleteName = participant.getAthlete().getUser().getFirstName() + " "
                + participant.getAthlete().getUser().getLastName();

        return new ParticipantResponse(
                participant.getId(),
                participant.getAthlete().getId(),
                athleteName,
                participant.getEvent().getId(),
                participant.getEvent().getName(),
                participant.getStatus(),
                participant.isCompliant(),
                participant.getRegisteredAt());
    }
}