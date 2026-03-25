package com.ciblorgasport.api.competition.repository;

import com.ciblorgasport.api.competition.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}