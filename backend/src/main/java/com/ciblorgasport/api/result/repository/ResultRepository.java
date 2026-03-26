package com.ciblorgasport.api.result.repository;

import com.ciblorgasport.api.result.entity.Result;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    boolean existsByParticipantId(Long participantId);
    List<Result> findByParticipantAthleteId(Long athleteId);
}