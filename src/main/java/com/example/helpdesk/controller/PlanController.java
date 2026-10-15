package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.PlanDto;
import com.example.helpdesk.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlanController {
    private final PlanService planService;
    @PostMapping("/api/plan/add")
    public void addPlan(@Valid @RequestBody PlanDto planDto)
    {
        planService.addPlan(planDto);
    }
}
