package com.ciblorgasport.api.competition.service;

import com.ciblorgasport.api.competition.dto.CompetitionResponse;
import com.ciblorgasport.api.competition.dto.CreateCompetitionRequest;
import com.ciblorgasport.api.competition.entity.Competition;
import com.ciblorgasport.api.competition.repository.CompetitionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<CompetitionResponse> getAllCompetitions() {
        return competitionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CompetitionResponse createCompetition(CreateCompetitionRequest request) {
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date");
        }

        Competition competition = new Competition();
        competition.setName(request.getName());
        competition.setType(request.getType());
        competition.setStartDate(request.getStartDate());
        competition.setEndDate(request.getEndDate());
        competition.setLocation(request.getLocation());

        Competition savedCompetition = competitionRepository.save(competition);
        return mapToResponse(savedCompetition);
    }

    private CompetitionResponse mapToResponse(Competition competition) {
        return new CompetitionResponse(
                competition.getId(),
                competition.getName(),
                competition.getType(),
                competition.getStartDate(),
                competition.getEndDate(),
                competition.getLocation());
    }
}