package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.ExecutiveDto;
import com.example.helpdesk.model.Executive;
import com.example.helpdesk.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;
    /*
     DTO (name,email,contact)
     validation
     Map DTO to Entity
     Insert Entity IN Db
     * */
    @PostMapping("/api/executive/add")
    public void insertExecutive(@Valid @RequestBody ExecutiveDto executiveDto,
                                Principal principal){
        executiveService.add(executiveDto, principal.getName()); // principal.getName() give us logged manager username
    }
}
