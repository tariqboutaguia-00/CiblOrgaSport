package com.ciblorgasport.api.auth.controller;

import com.ciblorgasport.api.auth.dto.LoginRequest;
import com.ciblorgasport.api.auth.dto.LoginResponse;
import com.ciblorgasport.api.auth.service.AuthService;
import com.ciblorgasport.api.common.ApiResponse;
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
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }
}