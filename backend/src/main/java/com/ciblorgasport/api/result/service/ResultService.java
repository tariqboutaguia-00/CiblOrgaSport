package com.ciblorgasport.api.result.service;

import com.ciblorgasport.api.participant.entity.Participant;
import com.ciblorgasport.api.participant.repository.ParticipantRepository;
import com.ciblorgasport.api.result.dto.AthletePerformanceResponse;
import com.ciblorgasport.api.result.dto.CreateResultRequest;
import com.ciblorgasport.api.result.dto.ResultResponse;
import com.ciblorgasport.api.result.entity.Result;
import com.ciblorgasport.api.result.repository.ResultRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
    }

    public List<ResultResponse> getAllResults() {
        return resultRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ResultResponse createResult(CreateResultRequest request) {
        Participant participant = participantRepository.findById(request.getParticipantId())
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        if (resultRepository.existsByParticipantId(participant.getId())) {
            throw new RuntimeException("Result already exists for this participant");
        }

        Result result = new Result();
        result.setParticipant(participant);
        result.setRankPosition(request.getRankPosition());
        result.setResultTime(request.getResultTime());
        result.setMedal(request.getMedal());
        result.setValidated(request.getValidated());

        Result savedResult = resultRepository.save(result);
        return mapToResponse(savedResult);
    }

    public List<AthletePerformanceResponse> getAthletePerformances(Long athleteId) {
        return resultRepository.findByParticipantAthleteId(athleteId)
                .stream()
                .map(this::mapToAthletePerformance)
                .toList();
    }

    public List<AthletePerformanceResponse> getAllPerformances() {
        return resultRepository.findAll()
                .stream()
                .map(this::mapToAthletePerformance)
                .toList();
    }

    private ResultResponse mapToResponse(Result result) {
        String athleteName = result.getParticipant().getAthlete().getUser().getFirstName() + " "
                + result.getParticipant().getAthlete().getUser().getLastName();

        return new ResultResponse(
                result.getId(),
                result.getParticipant().getId(),
                athleteName,
                result.getParticipant().getEvent().getName(),
                result.getRankPosition(),
                result.getResultTime(),
                result.getMedal(),
                result.isValidated());
    }

    private AthletePerformanceResponse mapToAthletePerformance(Result result) {
        String athleteName = result.getParticipant().getAthlete().getUser().getFirstName() + " "
                + result.getParticipant().getAthlete().getUser().getLastName();

        return new AthletePerformanceResponse(
                result.getId(),
                result.getParticipant().getAthlete().getId(),
                athleteName,
                result.getParticipant().getEvent().getName(),
                result.getRankPosition(),
                result.getResultTime(),
                result.getMedal(),
                result.isValidated());
    }
}