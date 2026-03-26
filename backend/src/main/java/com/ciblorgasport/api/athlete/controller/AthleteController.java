package com.ciblorgasport.api.athlete.controller;

import com.ciblorgasport.api.athlete.dto.AthleteResponse;
import com.ciblorgasport.api.athlete.dto.CreateAthleteRequest;
import com.ciblorgasport.api.athlete.dto.UpdateCharterRequest;
import com.ciblorgasport.api.athlete.service.AthleteService;
import com.ciblorgasport.api.common.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping
    public ApiResponse<List<AthleteResponse>> getAllAthletes() {
        return ApiResponse.success("Athletes retrieved successfully", athleteService.getAllAthletes());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AthleteResponse> createAthlete(@Valid @RequestBody CreateAthleteRequest request) {
        return ApiResponse.success("Athlete created successfully", athleteService.createAthlete(request));
    }

    @PatchMapping("/{athleteId}/charter")
    public ApiResponse<AthleteResponse> updateCharter(
            @PathVariable Long athleteId,
            @Valid @RequestBody UpdateCharterRequest request
    ) {
        return ApiResponse.success(
                "Athlete charter updated successfully",
                athleteService.updateCharter(athleteId, request.getCharterAccepted())
        );
    }
}