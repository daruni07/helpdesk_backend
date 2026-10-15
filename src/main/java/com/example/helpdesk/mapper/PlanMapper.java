package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.PlanDto;
import com.example.helpdesk.model.Plan;
import jakarta.validation.Valid;

public class PlanMapper {
    public static Plan mapToModel(@Valid PlanDto planDto) {
        Plan plan=new Plan();
        plan.setPlanType(planDto.planType());
        plan.setPrice(planDto.price());
        return plan;
    }
}
