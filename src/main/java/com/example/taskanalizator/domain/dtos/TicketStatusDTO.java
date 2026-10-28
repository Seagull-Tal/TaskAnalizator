package com.example.taskanalizator.domain.dtos;

public record TicketStatusDTO(
        String message,
        String oldStatus,
        String newStatus
) {
}
