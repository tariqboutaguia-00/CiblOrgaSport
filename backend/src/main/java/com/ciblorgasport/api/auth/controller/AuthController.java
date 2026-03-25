package com.ciblorgasport.api.auth.controller;

import com.ciblorgasport.api.auth.dto.LoginRequest;
import com.ciblorgasport.api.auth.dto.LoginResponse;
import com.ciblorgasport.api.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}