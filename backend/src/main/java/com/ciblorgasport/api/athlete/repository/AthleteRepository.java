package com.ciblorgasport.api.athlete.repository;

import com.ciblorgasport.api.athlete.entity.Athlete;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    boolean existsByUserId(Long userId);

    Optional<Athlete> findByUserId(Long userId);
}