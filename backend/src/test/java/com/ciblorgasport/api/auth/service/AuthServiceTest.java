package com.ciblorgasport.api.auth.service;

import com.ciblorgasport.api.auth.dto.LoginRequest;
import com.ciblorgasport.api.auth.dto.LoginResponse;
import com.ciblorgasport.api.user.Role;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthService(userRepository, passwordEncoder);
    }

    @Test
    void shouldLoginSuccessfully() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@ciblorgasport.com");
        request.setPassword("admin123");

        User user = new User();
        user.setFirstName("System");
        user.setLastName("Admin");
        user.setEmail("admin@ciblorgasport.com");
        user.setPassword("hashed-password");
        user.setRole(Role.ADMIN);
        user.setEnabled(true);

        when(userRepository.findByEmail("admin@ciblorgasport.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("admin123", "hashed-password")).thenReturn(true);

        LoginResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("admin@ciblorgasport.com", response.getEmail());
        assertEquals(Role.ADMIN, response.getRole());
        assertEquals("Login successful", response.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        LoginRequest request = new LoginRequest();
        request.setEmail("unknown@example.com");
        request.setPassword("password123");

        when(userRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Invalid email or password", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@ciblorgasport.com");
        request.setPassword("wrong-password");

        User user = new User();
        user.setFirstName("System");
        user.setLastName("Admin");
        user.setEmail("admin@ciblorgasport.com");
        user.setPassword("hashed-password");
        user.setRole(Role.ADMIN);

        when(userRepository.findByEmail("admin@ciblorgasport.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "hashed-password")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
        assertEquals("Invalid email or password", exception.getMessage());
    }
}