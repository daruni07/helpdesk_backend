package com.example.helpdesk.service;

import com.example.helpdesk.Exception.OwnershipException;
import com.example.helpdesk.Exception.ResourceNotFoundException;
import com.example.helpdesk.dto.request.TicketDto;
import com.example.helpdesk.dto.response.CustomerCountDto;
import com.example.helpdesk.dto.response.TicketExDto;
import com.example.helpdesk.dto.response.TicketInfoDto;
import com.example.helpdesk.dto.response.TicketRespDto;
import com.example.helpdesk.enums.Priority;
import com.example.helpdesk.enums.Status;
import com.example.helpdesk.mapper.TicketMapper;
import com.example.helpdesk.model.Customer;
import com.example.helpdesk.model.Executive;
import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.repository.CustomerRepository;
import com.example.helpdesk.repository.ExecutiveRepository;
import com.example.helpdesk.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;
    public void add(String customerUsername, TicketDto ticketDto) {
        // Step 1: Fetch Customer Object from DB using given customerId
        Customer customer =  customerRepository.getByUsername(customerUsername);

        // Step 2: Map TicketDto to Ticket entity
        Ticket ticket = TicketMapper.mapToModel(ticketDto);

        // Step 3: Attach customer to Ticket
        ticket.setCustomer(customer);

        // Step 3.5: Assign Status as Open to Ticket
        ticket.setStatus(Status.OPEN);

        // Step 4: Insert Ticket in DB
        ticketRepository.save(ticket);
    }
    public void assignExecutive(Long executiveId, Long ticketId, String managerUsername) {
        // Step 1: Fetch executive from DB using executiveId
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("executive not found"));
        // Step 1.5 Does this executive belong to the team of logged-in manager?
        if(! (executive.getManager().getUser().getUsername().equals(managerUsername))){
            throw new OwnershipException("Operation not allowed! contact admin");
        }
        // Step 2: Fetch ticket from DB using ticketId
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));

        // Step 3: Attach executive to ticket
        ticket.setExecutive(executive);

        // Step 4: Save ticket
        ticketRepository.save(ticket);
    }

    public List<TicketInfoDto> getTicketsByCustomerId(Long customerId, int page, int size) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id Invalid"));
        Pageable pageable= PageRequest.of(page,size);
        return ticketRepository.getTicketsByCustomerId(customerId,pageable);
    }

    public List<TicketInfoDto> getTicketsByCustomerUsername(String customerUsername, Integer page, Integer size) {
        Pageable pageable=PageRequest.of(page,size);
        return ticketRepository.getTicketsByCustomerUsername(customerUsername,pageable);
    }

    public List<TicketExDto> getTicketsByExecutiveUsername(String executiveUsername, Integer page, Integer size) {
        Pageable pageable=PageRequest.of(page,size);
        return ticketRepository.getTicketsByExecutiveUsername(executiveUsername,page,size);
    }

    public List<CustomerCountDto> getCustomerCount() {
        return ticketRepository.getCustomerCount();
    }

    public List<TicketRespDto> getTicketsByPriority(Priority priority) {
        List<Ticket> list = ticketRepository.findByPriority(priority);
        // Map List<Ticket> to List<TicketDto> using Mapper
        return list
                .stream()
                .map(TicketMapper :: convertEntityToDto)
                .toList();
    }
    public List<TicketRespDto> dynamicFilterTicket(Priority priority,
                                                   Status status,
                                                   LocalDate fromDate,
                                                   LocalDate toDate) {

        return ticketRepository.dynamicFilterTicket(priority,
                status,
                fromDate == null ? null: fromDate.atStartOfDay(ZoneOffset.UTC).toInstant(),
                toDate == null? null:toDate.atStartOfDay(ZoneOffset.UTC).toInstant());
    }
}
