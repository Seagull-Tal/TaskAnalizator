package com.example.taskanalizator.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StatusHistoryDTO(
        TicketDTO ticket,
        String fromStatus,
        String toStatus,
        Instant changedAt,
        String comment
) {
}
