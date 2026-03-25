package com.ciblorgasport.api.athlete.controller;

import com.ciblorgasport.api.athlete.dto.AthleteResponse;
import com.ciblorgasport.api.athlete.dto.CreateAthleteRequest;
import com.ciblorgasport.api.athlete.service.AthleteService;
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
    public List<AthleteResponse> getAllAthletes() {
        return athleteService.getAllAthletes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AthleteResponse createAthlete(@Valid @RequestBody CreateAthleteRequest request) {
        return athleteService.createAthlete(request);
    }
}