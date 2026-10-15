package com.example.helpdesk.dto.response;

import com.example.helpdesk.enums.PlanType;

import java.time.LocalDate;

public record CustomerRespDto(Long customerPlanId,
                              LocalDate activationDate,
                              LocalDate endDate,
                              String customerName,
                              PlanType planType) {
}
