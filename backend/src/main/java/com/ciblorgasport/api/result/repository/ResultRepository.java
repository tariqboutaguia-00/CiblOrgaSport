package com.ciblorgasport.api.result.repository;

import com.ciblorgasport.api.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    boolean existsByParticipantId(Long participantId);
}