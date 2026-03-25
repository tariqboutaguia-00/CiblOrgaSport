package com.ciblorgasport.api.competition.controller;

import com.ciblorgasport.api.competition.dto.CompetitionResponse;
import com.ciblorgasport.api.competition.dto.CreateCompetitionRequest;
import com.ciblorgasport.api.competition.service.CompetitionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<CompetitionResponse> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionResponse createCompetition(@Valid @RequestBody CreateCompetitionRequest request) {
        return competitionService.createCompetition(request);
    }
}