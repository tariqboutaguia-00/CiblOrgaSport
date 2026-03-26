package com.ciblorgasport.api.statistics.service;

import com.ciblorgasport.api.athlete.repository.AthleteRepository;
import com.ciblorgasport.api.competition.repository.CompetitionRepository;
import com.ciblorgasport.api.event.repository.EventRepository;
import com.ciblorgasport.api.incident.repository.IncidentRepository;
import com.ciblorgasport.api.mission.repository.MissionRepository;
import com.ciblorgasport.api.notification.repository.NotificationRepository;
import com.ciblorgasport.api.participant.repository.ParticipantRepository;
import com.ciblorgasport.api.result.repository.ResultRepository;
import com.ciblorgasport.api.statistics.dto.StatisticsResponse;
import com.ciblorgasport.api.user.repository.UserRepository;
import com.ciblorgasport.api.volunteer.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final UserRepository userRepository;
    private final CompetitionRepository competitionRepository;
    private final EventRepository eventRepository;
    private final AthleteRepository athleteRepository;
    private final ParticipantRepository participantRepository;
    private final ResultRepository resultRepository;
    private final IncidentRepository incidentRepository;
    private final NotificationRepository notificationRepository;
    private final VolunteerRepository volunteerRepository;
    private final MissionRepository missionRepository;

    public StatisticsService(
            UserRepository userRepository,
            CompetitionRepository competitionRepository,
            EventRepository eventRepository,
            AthleteRepository athleteRepository,
            ParticipantRepository participantRepository,
            ResultRepository resultRepository,
            IncidentRepository incidentRepository,
            NotificationRepository notificationRepository,
            VolunteerRepository volunteerRepository,
            MissionRepository missionRepository) {
        this.userRepository = userRepository;
        this.competitionRepository = competitionRepository;
        this.eventRepository = eventRepository;
        this.athleteRepository = athleteRepository;
        this.participantRepository = participantRepository;
        this.resultRepository = resultRepository;
        this.incidentRepository = incidentRepository;
        this.notificationRepository = notificationRepository;
        this.volunteerRepository = volunteerRepository;
        this.missionRepository = missionRepository;
    }

    public StatisticsResponse getOverview() {
        return new StatisticsResponse(
                userRepository.count(),
                competitionRepository.count(),
                eventRepository.count(),
                athleteRepository.count(),
                participantRepository.count(),
                resultRepository.count(),
                incidentRepository.count(),
                notificationRepository.count(),
                volunteerRepository.count(),
                missionRepository.count());
    }
}