package com.ciblorgasport.api.mission.repository;

import com.ciblorgasport.api.mission.entity.Mission;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByMissionDate(LocalDate missionDate);

    List<Mission> findByVolunteersIdAndMissionDate(Long volunteerId, LocalDate missionDate);
}