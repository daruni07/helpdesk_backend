package com.example.helpdesk.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        @NotNull(message = "name required")
        @NotBlank(message = "name required")
        String name,
        @NotNull(message = "city required")
        @NotBlank(message = "city required")
        String city,
        @Email(message = "email invalid")
        @NotBlank(message = "email required")
        @NotNull(message = "email required")
        String email,
        @NotBlank(message = "username required")
        @NotNull(message = "username required")
        String username,
        @NotBlank(message = "password required")
        @NotNull(message = "password required")
        String password
) {
}
