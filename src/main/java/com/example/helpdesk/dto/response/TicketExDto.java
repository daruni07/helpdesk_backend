package com.example.helpdesk.dto.response;

import com.example.helpdesk.enums.Priority;
import com.example.helpdesk.enums.Status;

import java.time.Instant;

public record TicketExDto(long ticketId, long executiveId, String executiveName,
                          String ticketSubject, Priority ticketPriority, Status ticketStatus,
                          Instant ticketCreatedAt,long customerId,String customerName) {
}
