package com.example.helpdesk.dto.request;

import com.example.helpdesk.enums.JobTitle;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public record ExecutiveDto(@NotNull(message = "name is required")
                           @NotBlank(message = "name is required")
                           String name,
                           @NotNull(message = "Email is required")
                           @NotBlank(message = "Email is required")
                           @Email(message = "email is not valid")
                           String email,
                           @NotNull(message = "Contact is required")
                           @NotBlank(message = "Contact is required")
                           @Size(min=10,max = 10)
                           String contact,
                           @NotBlank
                           @NotNull
                           @Size(min = 3, max = 15)
                           String username,
                           @NotBlank
                           @NotNull
                           String password,
                           JobTitle jobTitle) {
}
