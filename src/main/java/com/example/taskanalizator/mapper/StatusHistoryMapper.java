package com.example.taskanalizator.mapper;

import com.example.taskanalizator.domain.StatusHistoryEntity;
import com.example.taskanalizator.domain.dtos.StatusHistoryDTO;
import com.example.taskanalizator.domain.dtos.TicketDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusHistoryMapper {

    StatusHistoryDTO toDto(StatusHistoryEntity status, TicketDTO ticketDTO);
}
