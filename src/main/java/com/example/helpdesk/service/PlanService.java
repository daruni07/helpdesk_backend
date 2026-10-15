package com.example.helpdesk.service;

import com.example.helpdesk.dto.request.PlanDto;
import com.example.helpdesk.mapper.PlanMapper;
import com.example.helpdesk.model.Plan;
import com.example.helpdesk.repository.PlanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final PlanRepository planRepository;
    public void addPlan(@Valid PlanDto planDto) {
        Plan plan= PlanMapper.mapToModel(planDto);
        planRepository.save(plan);
    }
}
