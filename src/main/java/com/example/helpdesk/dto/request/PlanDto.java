package com.example.helpdesk.dto.request;

import com.example.helpdesk.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanDto(
        @NotNull(message = "plan type is required")
        PlanType planType,
        @NotNull(message = "price is required")
        Double price
) {

}
