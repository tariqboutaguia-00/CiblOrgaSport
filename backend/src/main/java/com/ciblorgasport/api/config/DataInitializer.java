package com.ciblorgasport.api.config;

import com.ciblorgasport.api.user.Role;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("System");
        admin.setEmail("admin@ciblorgasport.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        admin.setEnabled(true);

        User athlete = new User();
        athlete.setFirstName("Tariq");
        athlete.setLastName("Swimmer");
        athlete.setEmail("tariq@ciblorgasport.com");
        athlete.setPassword(passwordEncoder.encode("password"));
        athlete.setRole(Role.ATHLETE);
        athlete.setEnabled(true);

        User volunteer = new User();
        volunteer.setFirstName("Sara");
        volunteer.setLastName("Helper");
        volunteer.setEmail("sara@ciblorgasport.com");
        volunteer.setPassword(passwordEncoder.encode("password"));
        volunteer.setRole(Role.VOLUNTEER);
        volunteer.setEnabled(true);

        userRepository.save(admin);
        userRepository.save(athlete);
        userRepository.save(volunteer);
    }
}