package com.ciblorgasport.api.incident.repository;

import com.ciblorgasport.api.incident.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}