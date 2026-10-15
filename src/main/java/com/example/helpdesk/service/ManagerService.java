package com.example.helpdesk.service;

import com.example.helpdesk.Exception.ResourceNotFoundException;
import com.example.helpdesk.dto.request.ManagerDto;
import com.example.helpdesk.dto.response.JobTitleExecutiveCountDto;
import com.example.helpdesk.enums.JobTitle;
import com.example.helpdesk.enums.Role;
import com.example.helpdesk.mapper.ManagerMapper;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ExecutiveRepository executiveRepository;
    private final PasswordEncoder passwordEncoder;

    public Manager add(@Valid ManagerDto managerDto) {

        // Step 1: Prepare user object and save it in DB
        User user = new User();
        user.setUsername(managerDto.username());
        user.setPassword(passwordEncoder.encode(managerDto.password()));
        user.setRole(Role.MANAGER);
        user = userRepository.save(user); //<-- This user now has an id attached

        // Step 2: Map Dto to Entity
        Manager manager = ManagerMapper.mapToModel(managerDto);

        // Step 3: Attach User to Manager
        manager.setUser(user);

        // Step 4: save manager
        return managerRepository.save(manager);
    }

    public List<JobTitleExecutiveCountDto> getExecutiveStatWithJobTitleAndCount() {
        return managerRepository.getExecutiveStatWithJobTitleAndCount();
    }

    public void updateJobTitle(long executiveId, JobTitle jobTitle) {
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("Executive Not Found"));

        // Step 2: Update job title
        executive.setJobTitle(jobTitle);

        // Step 3: Save in DB
        executiveRepository.save(executive);
    }
}
