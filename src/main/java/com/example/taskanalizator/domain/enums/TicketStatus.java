package com.example.taskanalizator.domain.enums;

public enum TicketStatus {
    NEW,
    IN_PROGRESS,
    RESOLVED,
    CANCELLED;

    public static TicketStatus fromString(String value) {

        for (TicketStatus status : TicketStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw  new IllegalArgumentException(value + " is not a valid TicketStatus");
    }
}
