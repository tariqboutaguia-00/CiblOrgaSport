package com.ciblorgasport.api.event.repository;

import com.ciblorgasport.api.event.entity.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCompetitionId(Long competitionId);
}