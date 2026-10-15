package com.example.helpdesk.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerPlanDto(
        @NotNull(message = "Activation date required")
        LocalDate activationDate,
        @NotNull(message = "End date required")
        LocalDate endDate,
        String coupon,
        @NotNull(message = "amount paid is required")
        Double amountPaid
) {
}
