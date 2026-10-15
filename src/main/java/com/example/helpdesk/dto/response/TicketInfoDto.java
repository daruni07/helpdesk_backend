package com.example.helpdesk.dto.response;

import com.example.helpdesk.enums.Priority;
import com.example.helpdesk.enums.Status;

import java.time.Instant;

public record TicketInfoDto(Long ticketId,
                            String subject,
                            Instant createdAt,
                            Priority priority,
                            Status status,
                            String customerName,
                            String executiveName,
                            String executiveEmail) {
}
