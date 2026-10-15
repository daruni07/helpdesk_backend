package com.example.helpdesk.controller;

import com.example.helpdesk.dto.request.TicketDto;
import com.example.helpdesk.dto.response.*;
import com.example.helpdesk.enums.Priority;
import com.example.helpdesk.enums.Status;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.service.TicketService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TicketController {
    private final TicketService ticketService;
    @PostMapping("/api/ticket/add")
    public void postTicket(Principal principal,
                           @Valid @RequestBody TicketDto ticketDto){
        ticketService.add(principal.getName(), ticketDto);
    }
    // Done by Manager - activate the check after security config
    @PutMapping("/api/ticket/assign/{executiveId}/{ticketId}")
    public void assignExecutive(@PathVariable Long  executiveId ,
                                @PathVariable Long ticketId,
                                Principal principal){
        ticketService.assignExecutive(executiveId, ticketId, principal.getName());
    }
    @GetMapping("/api/ticket/v1/{customer_id}")
    public List<TicketInfoDto> getTicketsByCustomerId(@PathVariable Long customer_id,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size)
    {
        return ticketService.getTicketsByCustomerId(customer_id,page,size);
    }
    @GetMapping("/api/ticket/v2")
    public List<TicketInfoDto> getTicketsByCustomerUsername(@RequestParam("username") String customerUsername,
                                                            @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return ticketService.getTicketsByCustomerUsername(customerUsername, page, size);
    }
    @GetMapping("/api/ticket/exec")
    public List<TicketExDto> getTicketsByExecutiveUsername(@RequestParam("username")String executiveUsername,
                                                           @RequestParam(name="page",required=false,defaultValue = "0") Integer page,
                                                           @RequestParam(name="size",required = false,defaultValue = "20")Integer size)
    {
        return ticketService.getTicketsByExecutiveUsername(executiveUsername,page,size);
    }
    @GetMapping("/api/ticket/stat/customer/count")
    public List<CustomerCountDto> getCustomerCount(){
        return ticketService.getCustomerCount();
    }
    @GetMapping("/api/ticket/by-priority")
    public List<TicketRespDto> getTicketsByPriority(@RequestParam("priority") Priority priority){
        return ticketService.getTicketsByPriority(priority);
    }
    @GetMapping("/api/ticket/filter")
    public List<TicketRespDto> dynamicFilterTicket(@RequestParam(value = "priority", required = false) Priority priority,
                                                   @RequestParam(value = "status", required = false) Status status,
                                                   @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                                                   @RequestParam(value = "toDate", required = false) LocalDate toDate
    ){
        return ticketService.dynamicFilterTicket(priority,status, fromDate, toDate);
    }
}
