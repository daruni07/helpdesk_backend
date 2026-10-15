package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.ManagerDto;
import com.example.helpdesk.dto.response.JobTitleExecutiveCountDto;
import com.example.helpdesk.enums.JobTitle;
import com.example.helpdesk.model.Manager;
import com.example.helpdesk.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    @PostMapping("/api/manager/add")
    public void add(@Valid @RequestBody ManagerDto managerDto){
        managerService.add(managerDto);
    }
     /*
    JobTitle            |   No of Executives
 FIRST_LINE_SUPPORT             2
 SECOND_LINE_SUPPORT            3

for(){ //100   [N+1 problem]
   --> DB (3)  : 100
}

GroupBy
-------
FIRST_LINE_SUPPORT    count(id)

    Name of Executive  |  No of Tickets   |   No of CLOSED Tickets  |


    * */
     @GetMapping("/api/manager/jobtitle/stat/executive/count")
     public List<JobTitleExecutiveCountDto> getExecutiveStatWithJobTitleAndCount(){
         return managerService.getExecutiveStatWithJobTitleAndCount();
     }
    @PutMapping("/api/jobtitle/update/{executiveId}")
    public void updateJobTitle(@PathVariable long executiveId,
                               @RequestParam("jobTitle") JobTitle jobTitle){
        managerService.updateJobTitle(executiveId,jobTitle);}
}
