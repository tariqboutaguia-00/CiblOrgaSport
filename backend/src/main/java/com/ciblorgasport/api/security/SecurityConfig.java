package com.ciblorgasport.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/actuator/health").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/users").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/competitions").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/competitions").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/events").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/events").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/athletes").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/athletes").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/participants").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/participants").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/results").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/results").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/incidents").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/incidents").hasAnyRole("COMMISSIONER","ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/notifications").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/notifications").hasAnyRole("DEPLOYMENT_MANAGER","ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/volunteers").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/volunteers").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/missions").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/missions/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/statistics").hasAnyRole("DEPLOYMENT_MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/athletes/*/charter").hasAnyRole("ATHLETE", "ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/participants/*/withdraw").hasAnyRole("ATHLETE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/results/athlete/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/results/performances").authenticated()

                        .requestMatchers(HttpMethod.PATCH, "/api/participants/*/compliance").hasAnyRole("COMMISSIONER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/events/*/cancel").hasAnyRole("COMMISSIONER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/events/*/reschedule").hasAnyRole("COMMISSIONER", "ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/events/*/meeting-points").hasAnyRole("DEPLOYMENT_MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/users/*/access").hasAnyRole("DEPLOYMENT_MANAGER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/notifications/subscribe").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/notifications/user/**").authenticated()

                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}