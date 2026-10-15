package com.example.helpdesk.dto.response;

import com.example.helpdesk.enums.Status;

public record CustomerCountDto(Status ticketStaus,Long count) {
}
