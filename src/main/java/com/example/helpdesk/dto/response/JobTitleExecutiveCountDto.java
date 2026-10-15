package com.example.helpdesk.dto.response;

import com.example.helpdesk.enums.JobTitle;

public record JobTitleExecutiveCountDto(JobTitle jobTitle,
                                        long numberOfExecutives) {
}
