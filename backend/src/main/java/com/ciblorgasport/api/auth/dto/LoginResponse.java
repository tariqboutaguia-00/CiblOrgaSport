package com.ciblorgasport.api.auth.dto;

import com.ciblorgasport.api.user.Role;

public class LoginResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String message;

    public LoginResponse(Long id, String firstName, String lastName, String email, Role role, String message) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}