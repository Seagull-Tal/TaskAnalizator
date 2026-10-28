package com.example.taskanalizator.domain.enums;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public static Priority fromString(String value) {
        for (Priority priority : Priority.values()) {
            if (priority.name().equalsIgnoreCase(value)) {
                return priority;
            }
        }

        throw new IllegalArgumentException(value + " is not a valid Priority");
    }
}
