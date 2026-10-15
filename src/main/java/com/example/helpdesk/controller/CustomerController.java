package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.CustomerDto;
import com.example.helpdesk.dto.request.CustomerUpdateDto;
import com.example.helpdesk.model.Customer;
import com.example.helpdesk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/api/customer/add")
    public void insertCustomer(@RequestBody CustomerDto dto)
    {
        customerService.insertCustomer(dto);
    }
    @GetMapping("/api/customer/all")
    public List<Customer> getAll()
    {
        return customerService.getAll();
    }
    @GetMapping("/api/customer/{id}")
    public Customer getById(@PathVariable long id)
    {
        return customerService.getById(id);
    }
    @DeleteMapping("/api/customer/{id}")
    public void deleteById(@PathVariable long id)
    {
        customerService.deleteById(id);
    }
    @PutMapping("/api/customer/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody CustomerUpdateDto dto){
        customerService.update(id, dto);
    }
}
