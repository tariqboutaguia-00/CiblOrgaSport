package com.ciblorgasport.api.participant.repository;

import com.ciblorgasport.api.participant.entity.Participant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    boolean existsByAthleteIdAndEventId(Long athleteId, Long eventId);
    List<Participant> findByAthleteId(Long athleteId);
}