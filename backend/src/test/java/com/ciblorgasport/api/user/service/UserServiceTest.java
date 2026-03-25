package com.ciblorgasport.api.user.service;

import com.ciblorgasport.api.user.Role;
import com.ciblorgasport.api.user.dto.CreateUserRequest;
import com.ciblorgasport.api.user.dto.UserResponse;
import com.ciblorgasport.api.user.entity.User;
import com.ciblorgasport.api.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Tariq");
        request.setLastName("Test");
        request.setEmail("tariq1@example.com");
        request.setPassword("password123");
        request.setRole(Role.ATHLETE);

        when(userRepository.existsByEmail("tariq1@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashed-password");

        User savedUser = new User();
        savedUser.setFirstName("Tariq");
        savedUser.setLastName("Test");
        savedUser.setEmail("tariq1@example.com");
        savedUser.setPassword("hashed-password");
        savedUser.setRole(Role.ATHLETE);
        savedUser.setEnabled(true);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("Tariq", response.getFirstName());
        assertEquals("tariq1@example.com", response.getEmail());
        assertEquals(Role.ATHLETE, response.getRole());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User userToSave = captor.getValue();
        assertEquals("Tariq", userToSave.getFirstName());
        assertEquals("hashed-password", userToSave.getPassword());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Tariq");
        request.setLastName("Test");
        request.setEmail("tariq1@example.com");
        request.setPassword("password123");
        request.setRole(Role.ATHLETE);

        when(userRepository.existsByEmail("tariq1@example.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.createUser(request));
        assertEquals("Email already exists", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}