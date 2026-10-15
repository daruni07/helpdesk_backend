package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.CustomerPlanDto;
import com.example.helpdesk.dto.response.CustomerRespDto;
import com.example.helpdesk.service.CustomerPlanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerPlanController {
    private final CustomerPlanService customerPlanService;
    /*
    API to add customer buys plan record in customer_plan table with all relevant fields
    * */

    /*
     * GET API : to fetch all plans based on customer username.
     * */
    @PostMapping("/api/customerplan/add")
    public void insertCustomerPlan(@Valid@RequestBody CustomerPlanDto customerPlanDto,
                                   @RequestParam Long customerId,
                                   @RequestParam Long planId)
    {
        customerPlanService.insertCustomerPlan(customerPlanDto,customerId,planId);
    }
    @GetMapping("/api/customerplan/v1")
    public List<CustomerRespDto> getCustomerPlansByCustomerUsername(@RequestParam("username")String customerUsername,
                                                                    @RequestParam(name="page",required=false,defaultValue = "0") Integer page,
                                                                    @RequestParam(name="size",required = false,defaultValue = "20")Integer size)
    {
        return customerPlanService.getCustomerPlansByUsername(customerUsername,page,size);
    }
}
