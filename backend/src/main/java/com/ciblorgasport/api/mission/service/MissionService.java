package com.ciblorgasport.api.mission.service;

import com.ciblorgasport.api.mission.dto.CreateMissionRequest;
import com.ciblorgasport.api.mission.dto.MissionResponse;
import com.ciblorgasport.api.mission.entity.Mission;
import com.ciblorgasport.api.mission.repository.MissionRepository;
import com.ciblorgasport.api.volunteer.entity.Volunteer;
import com.ciblorgasport.api.volunteer.repository.VolunteerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final VolunteerRepository volunteerRepository;

    public MissionService(MissionRepository missionRepository, VolunteerRepository volunteerRepository) {
        this.missionRepository = missionRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public List<MissionResponse> getAllMissions() {
        return missionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MissionResponse createMission(CreateMissionRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new RuntimeException("End time cannot be before start time");
        }

        Mission mission = new Mission();
        mission.setTitle(request.getTitle());
        mission.setDescription(request.getDescription());
        mission.setMissionDate(request.getMissionDate());
        mission.setStartTime(request.getStartTime());
        mission.setEndTime(request.getEndTime());
        mission.setLocation(request.getLocation());

        Mission savedMission = missionRepository.save(mission);
        return mapToResponse(savedMission);
    }

    public MissionResponse assignVolunteer(Long missionId, Long volunteerId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));

        mission.getVolunteers().add(volunteer);
        Mission savedMission = missionRepository.save(mission);

        return mapToResponse(savedMission);
    }

    private MissionResponse mapToResponse(Mission mission) {
        List<String> volunteerNames = mission.getVolunteers()
                .stream()
                .map(volunteer -> volunteer.getUser().getFirstName() + " " + volunteer.getUser().getLastName())
                .toList();

        return new MissionResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getDescription(),
                mission.getMissionDate(),
                mission.getStartTime(),
                mission.getEndTime(),
                mission.getLocation(),
                volunteerNames);
    }
}