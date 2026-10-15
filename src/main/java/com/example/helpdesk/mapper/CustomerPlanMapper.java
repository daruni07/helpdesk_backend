package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.CustomerPlanDto;
import com.example.helpdesk.model.CustomerPlan;
import jakarta.validation.Valid;

public class CustomerPlanMapper {

    public static CustomerPlan mapToModel(@Valid CustomerPlanDto customerPlanDto) {
            CustomerPlan customerPlan=new CustomerPlan();
            customerPlan.setActivationDate(customerPlanDto.activationDate());
            customerPlan.setEndDate(customerPlanDto.endDate());
            customerPlan.setAmountPaid(customerPlanDto.amountPaid());
            customerPlan.setCoupon(customerPlanDto.coupon());
            return customerPlan;
    }
}
