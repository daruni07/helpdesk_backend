package com.example.helpdesk.service;

import com.example.helpdesk.Exception.ResourceNotFoundException;
import com.example.helpdesk.dto.request.CustomerPlanDto;
import com.example.helpdesk.dto.response.CustomerRespDto;
import com.example.helpdesk.mapper.CustomerMapper;
import com.example.helpdesk.mapper.CustomerPlanMapper;
import com.example.helpdesk.model.Customer;
import com.example.helpdesk.model.CustomerPlan;
import com.example.helpdesk.model.Plan;
import com.example.helpdesk.repository.CustomerPlanRepository;
import com.example.helpdesk.repository.CustomerRepository;
import com.example.helpdesk.repository.PlanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerPlanService {

    private final CustomerPlanRepository customerPlanRepository;
    private final CustomerRepository customerRepository;
    private final PlanRepository planRepository;

    public void insertCustomerPlan(CustomerPlanDto customerPlanDto, Long customerId, Long planId) {
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid customer id"));
        Plan plan=planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid plan id"));

        CustomerPlan customerPlan=CustomerPlanMapper.mapToModel(customerPlanDto);
        customerPlan.setPlan(plan);
        customerPlan.setCustomer(customer);
        customerPlanRepository.save(customerPlan);
    }

    public List<CustomerRespDto> getCustomerPlansByUsername(String customerUsername, Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return customerPlanRepository.getCustomerPlansByCustomerUsername(customerUsername,pageable);
    }
}
