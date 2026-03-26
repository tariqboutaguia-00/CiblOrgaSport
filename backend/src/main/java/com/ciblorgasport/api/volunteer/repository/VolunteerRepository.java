package com.ciblorgasport.api.volunteer.repository;

import com.ciblorgasport.api.volunteer.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    boolean existsByUserId(Long userId);
}