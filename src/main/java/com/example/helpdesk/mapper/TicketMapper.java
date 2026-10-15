package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.TicketDto;
import com.example.helpdesk.dto.response.TicketRespDto;
import com.example.helpdesk.model.Ticket;
import jakarta.validation.Valid;

public class TicketMapper {
    public static Ticket mapToModel(@Valid TicketDto dto) {
        Ticket ticket=new Ticket();
        ticket.setSubject(dto.subject());
        ticket.setIssue(dto.issue());
        ticket.setPriority(dto.priority());
        return ticket;
    }
    public static TicketRespDto convertEntityToDto(Ticket ticket){
        return
                new TicketRespDto (
                        ticket.getId(),
                        ticket.getSubject(),
                        ticket.getCreatedAt(),
                        ticket.getPriority(),
                        ticket.getStatus()
                );
    }
}
