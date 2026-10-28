package com.example.taskanalizator.domain.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateTicketDTO(
        String title,
        String description,
        String status,
        CategoryDTO category
) {
}
