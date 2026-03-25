package com.ciblorgasport.api.participant.controller;

import com.ciblorgasport.api.participant.dto.CreateParticipantRequest;
import com.ciblorgasport.api.participant.dto.ParticipantResponse;
import com.ciblorgasport.api.participant.service.ParticipantService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<ParticipantResponse> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantResponse createParticipant(@Valid @RequestBody CreateParticipantRequest request) {
        return participantService.createParticipant(request);
    }
}