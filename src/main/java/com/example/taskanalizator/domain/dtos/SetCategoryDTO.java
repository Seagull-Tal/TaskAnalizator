package com.example.taskanalizator.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SetCategoryDTO(
        String name,
        String description
) {
}
