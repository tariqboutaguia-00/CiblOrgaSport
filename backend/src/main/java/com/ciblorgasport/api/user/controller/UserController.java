package com.ciblorgasport.api.user.controller;

import com.ciblorgasport.api.common.ApiResponse;
import com.ciblorgasport.api.user.dto.CreateUserRequest;
import com.ciblorgasport.api.user.dto.UpdateUserAccessRequest;
import com.ciblorgasport.api.user.dto.UserResponse;
import com.ciblorgasport.api.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success("Users retrieved successfully", userService.getAllUsers());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ApiResponse.success("User created successfully", userService.createUser(request));
    }

    @PatchMapping("/{userId}/access")
    public ApiResponse<UserResponse> updateUserAccess(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserAccessRequest request) {
        return ApiResponse.success(
                "User access updated successfully",
                userService.updateUserAccess(userId, request.getEnabled()));
    }
}