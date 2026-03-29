package com.ciblorgasport.api.mission.controller;

import com.ciblorgasport.api.common.ApiResponse;
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
    public ApiResponse<List<MissionResponse>> getAllMissions() {
        return ApiResponse.success("Missions retrieved successfully", missionService.getAllMissions());
    }

    @GetMapping("/volunteer/{volunteerId}/today")
    public ApiResponse<List<MissionResponse>> getVolunteerTodayMissions(@PathVariable Long volunteerId) {
        return ApiResponse.success(
                "Volunteer daily missions retrieved successfully",
                missionService.getVolunteerTodayMissions(volunteerId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MissionResponse> createMission(@Valid @RequestBody CreateMissionRequest request) {
        return ApiResponse.success("Mission created successfully", missionService.createMission(request));
    }

    @PostMapping("/{missionId}/assign/{volunteerId}")
    public ApiResponse<MissionResponse> assignVolunteer(@PathVariable Long missionId, @PathVariable Long volunteerId) {
        return ApiResponse.success(
                "Volunteer assigned successfully",
                missionService.assignVolunteer(missionId, volunteerId));
    }
}