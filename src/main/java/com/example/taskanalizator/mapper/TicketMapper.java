package com.example.taskanalizator.mapper;

import com.example.taskanalizator.domain.TicketEntity;
import com.example.taskanalizator.domain.dtos.CategoryDTO;
import com.example.taskanalizator.domain.dtos.CreateTicketDTO;
import com.example.taskanalizator.domain.dtos.StatusHistoryDTO;
import com.example.taskanalizator.domain.dtos.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    TicketEntity toEntity(CreateTicketDTO ticket);

    @Mapping(target = "description", source = "ticket.description")
    TicketDTO toTicketDTO(TicketEntity ticket, CategoryDTO category, List<StatusHistoryDTO> statusHistoryDTOS);


}
