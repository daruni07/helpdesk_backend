package com.example.helpdesk.mapper;

import com.example.helpdesk.dto.request.ManagerDto;
import com.example.helpdesk.model.Manager;

public class ManagerMapper {
    public static Manager mapToModel(ManagerDto dto)
    {
        Manager manager = new Manager();
        manager.setName(dto.name());
        return manager;
    }
}
