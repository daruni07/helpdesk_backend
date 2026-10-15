package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.CustomerDto;
import com.example.helpdesk.model.Customer;

public class CustomerMapper {
    public static Customer mapToModel(CustomerDto dto) {
        Customer customer=new Customer();
        customer.setName(dto.name());
        customer.setCity(dto.city());
        customer.setEmail(dto.email());
        return customer;
    }
}
