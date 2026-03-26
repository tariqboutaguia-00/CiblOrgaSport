package com.ciblorgasport.api.volunteer.service;

import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import com.ciblorgasport.api.volunteer.dto.CreateVolunteerRequest;
import com.ciblorgasport.api.volunteer.dto.VolunteerResponse;
import com.ciblorgasport.api.volunteer.entity.Volunteer;
import com.ciblorgasport.api.volunteer.repository.VolunteerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final UserRepository userRepository;

    public VolunteerService(VolunteerRepository volunteerRepository, UserRepository userRepository) {
        this.volunteerRepository = volunteerRepository;
        this.userRepository = userRepository;
    }

    public List<VolunteerResponse> getAllVolunteers() {
        return volunteerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VolunteerResponse createVolunteer(CreateVolunteerRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (volunteerRepository.existsByUserId(user.getId())) {
            throw new RuntimeException("Volunteer profile already exists for this user");
        }

        Volunteer volunteer = new Volunteer();
        volunteer.setUser(user);
        volunteer.setCenNumber(request.getCenNumber());
        volunteer.setFederation(request.getFederation());
        volunteer.setAccreditationStatus(request.getAccreditationStatus());

        Volunteer savedVolunteer = volunteerRepository.save(volunteer);
        return mapToResponse(savedVolunteer);
    }

    private VolunteerResponse mapToResponse(Volunteer volunteer) {
        return new VolunteerResponse(
                volunteer.getId(),
                volunteer.getUser().getId(),
                volunteer.getUser().getFirstName(),
                volunteer.getUser().getLastName(),
                volunteer.getUser().getEmail(),
                volunteer.getCenNumber(),
                volunteer.getFederation(),
                volunteer.getAccreditationStatus());
    }
}