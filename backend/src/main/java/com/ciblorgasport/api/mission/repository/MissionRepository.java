package com.ciblorgasport.api.mission.repository;

import com.ciblorgasport.api.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}