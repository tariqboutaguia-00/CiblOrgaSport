package com.ciblorgasport.api.participant.controller;

import com.ciblorgasport.api.common.ApiResponse;
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
    public ApiResponse<List<ParticipantResponse>> getAllParticipants() {
        return ApiResponse.success("Participants retrieved successfully", participantService.getAllParticipants());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ParticipantResponse> createParticipant(@Valid @RequestBody CreateParticipantRequest request) {
        return ApiResponse.success("Participant created successfully", participantService.createParticipant(request));
    }

    @PatchMapping("/{participantId}/withdraw")
    public ApiResponse<ParticipantResponse> withdrawParticipant(@PathVariable Long participantId) {
        return ApiResponse.success(
                "Participant withdrawn successfully",
                participantService.withdrawParticipant(participantId));
    }
}