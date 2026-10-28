package com.example.taskanalizator.domain.dtos;


import com.example.taskanalizator.domain.enums.Priority;
import com.example.taskanalizator.domain.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TicketDTO(
        String title,
        String description,
        TicketStatus status,
        Priority priority,
        CategoryDTO category,
        Instant createdAt,
        Instant dueDate,
        List<StatusHistoryDTO> statusHistoryDTOS
) {
}
