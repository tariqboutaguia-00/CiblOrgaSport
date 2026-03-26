package com.ciblorgasport.api.volunteer.controller;

import com.ciblorgasport.api.volunteer.dto.CreateVolunteerRequest;
import com.ciblorgasport.api.volunteer.dto.VolunteerResponse;
import com.ciblorgasport.api.volunteer.service.VolunteerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public List<VolunteerResponse> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VolunteerResponse createVolunteer(@Valid @RequestBody CreateVolunteerRequest request) {
        return volunteerService.createVolunteer(request);
    }
}