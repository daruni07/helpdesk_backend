package com.example.helpdesk.service;

import com.example.helpdesk.Exception.InvalidCallException;
import com.example.helpdesk.Exception.ResourceNotFoundException;
import com.example.helpdesk.dto.request.CustomerDto;
import com.example.helpdesk.dto.request.CustomerUpdateDto;
import com.example.helpdesk.enums.Role;
import com.example.helpdesk.mapper.CustomerMapper;
import com.example.helpdesk.model.Customer;
import com.example.helpdesk.model.Executive;
import com.example.helpdesk.model.User;
import com.example.helpdesk.repository.CustomerRepository;
import com.example.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public  Customer getById(long id) {
        Optional<Customer> optional= customerRepository.findById(id);
        if(optional.isEmpty())
        {
            throw new ResourceNotFoundException("Invalid id");
        }
        return optional.get();
    }

    public void insertCustomer(CustomerDto dto)
    {
        User user = userService.getUserObj(
                dto.username(),
                dto.password(),
                Role.CUSTOMER
        );

        // Step 1.5 : Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Map CustomerDto to Customer entity
        Customer customer = CustomerMapper.mapToModel(dto);
        // Attach user to Customer
        customer.setUser(user);

        // Save Customer entity
        customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void deleteById(long id) {
        customerRepository.deleteById(id);
    }

    public void update(Long id, CustomerUpdateDto dto) {
        Customer customer = getById(id);
        boolean flag = false;

        if(!(dto.name()  == null) && !(dto.name().trim().isEmpty())) {    // If dto.name() is NOT(!) empty
            customer.setName(dto.name());
            flag = true;
        }

        if(!(dto.city() == null) && !(dto.city().trim().isEmpty())) {
            customer.setCity(dto.city());
            flag = true;
        }

        if(!flag)
            throw new InvalidCallException("Update op terminated");

        customerRepository.save(customer);

    }

}
