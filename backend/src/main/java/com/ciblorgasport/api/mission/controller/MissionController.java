package com.ciblorgasport.api.mission.controller;

import com.ciblorgasport.api.mission.dto.CreateMissionRequest;
import com.ciblorgasport.api.mission.dto.MissionResponse;
import com.ciblorgasport.api.mission.service.MissionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<MissionResponse> getAllMissions() {
        return missionService.getAllMissions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MissionResponse createMission(@Valid @RequestBody CreateMissionRequest request) {
        return missionService.createMission(request);
    }

    @PostMapping("/{missionId}/assign/{volunteerId}")
    public MissionResponse assignVolunteer(@PathVariable Long missionId, @PathVariable Long volunteerId) {
        return missionService.assignVolunteer(missionId, volunteerId);
    }
}