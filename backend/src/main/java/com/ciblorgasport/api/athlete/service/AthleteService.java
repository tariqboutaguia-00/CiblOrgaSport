package com.ciblorgasport.api.athlete.service;

import com.ciblorgasport.api.athlete.dto.AthleteResponse;
import com.ciblorgasport.api.athlete.dto.CreateAthleteRequest;
import com.ciblorgasport.api.athlete.entity.Athlete;
import com.ciblorgasport.api.athlete.repository.AthleteRepository;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final UserRepository userRepository;

    public AthleteService(AthleteRepository athleteRepository, UserRepository userRepository) {
        this.athleteRepository = athleteRepository;
        this.userRepository = userRepository;
    }

    public List<AthleteResponse> getAllAthletes() {
        return athleteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AthleteResponse createAthlete(CreateAthleteRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (athleteRepository.existsByUserId(user.getId())) {
            throw new RuntimeException("Athlete profile already exists for this user");
        }

        Athlete athlete = new Athlete();
        athlete.setUser(user);
        athlete.setNationality(request.getNationality());
        athlete.setPassportExpiration(request.getPassportExpiration());
        athlete.setMedicalCertificateExpiration(request.getMedicalCertificateExpiration());
        athlete.setCharterAccepted(request.getCharterAccepted());

        Athlete savedAthlete = athleteRepository.save(athlete);
        return mapToResponse(savedAthlete);
    }

    public AthleteResponse updateCharter(Long athleteId, boolean charterAccepted) {
        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        athlete.setCharterAccepted(charterAccepted);
        Athlete savedAthlete = athleteRepository.save(athlete);

        return mapToResponse(savedAthlete);
    }

    private AthleteResponse mapToResponse(Athlete athlete) {
        return new AthleteResponse(
                athlete.getId(),
                athlete.getUser().getId(),
                athlete.getUser().getFirstName(),
                athlete.getUser().getLastName(),
                athlete.getUser().getEmail(),
                athlete.getNationality(),
                athlete.getPassportExpiration(),
                athlete.getMedicalCertificateExpiration(),
                athlete.isCharterAccepted());
    }
}