package com.ciblorgasport.api.incident.controller;

import com.ciblorgasport.api.incident.dto.CreateIncidentRequest;
import com.ciblorgasport.api.incident.dto.IncidentResponse;
import com.ciblorgasport.api.incident.service.IncidentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<IncidentResponse> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidentResponse createIncident(@Valid @RequestBody CreateIncidentRequest request) {
        return incidentService.createIncident(request);
    }
}