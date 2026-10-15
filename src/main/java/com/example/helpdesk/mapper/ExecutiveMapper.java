package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.ExecutiveDto;
import com.example.helpdesk.model.Executive;

public class ExecutiveMapper {
    public static Executive mapToModel(ExecutiveDto dto)
    {
        Executive executive = new Executive();
        executive.setName(dto.name());
        executive.setEmail(dto.email());
        executive.setContact(dto.contact());
        executive.setJobTitle(dto.jobTitle());
        return executive;
    }
}
