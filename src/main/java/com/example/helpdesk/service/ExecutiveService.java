package com.example.helpdesk.service;

import com.example.helpdesk.Exception.ResourceNotFoundException;
import com.example.helpdesk.dto.request.ExecutiveDto;
import com.example.helpdesk.enums.Role;
import com.example.helpdesk.mapper.ExecutiveMapper;
import com.example.helpdesk.model.Executive;
import com.example.helpdesk.model.Manager;
import com.example.helpdesk.model.User;
import com.example.helpdesk.repository.ExecutiveRepository;
import com.example.helpdesk.repository.ManagerRepository;
import com.example.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


        public void add(@Valid ExecutiveDto executiveDto, String managerUsername) {
            // Step 0: Fetch manager from DB using managerUsername
            Manager manager =  managerRepository.getByUsername(managerUsername);

            // Step 1: Prepare User object and save it in DB
        /*
        Executive
            User <-- User must go in DB first and then Executive next
        * */
            User user = userService.getUserObj(  // <-- this is a full created user obj with id
                    executiveDto.username(),
                    executiveDto.password(),
                    Role.EXECUTIVE
            );

            // Step 1.5 Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Step Map ExecutiveDto to Executive entity
            Executive executive = ExecutiveMapper.mapToModel(executiveDto);

            // Step 3: Attach the User & Manager to Executive
            executive.setUser(user);
            executive.setManager(manager);

            // Step 4: Save executive and return
            executiveRepository.save(executive);
        }

    }

