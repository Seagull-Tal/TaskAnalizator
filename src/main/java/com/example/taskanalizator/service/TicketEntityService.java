package com.example.taskanalizator.service;

import com.example.taskanalizator.domain.CategoryEntity;
import com.example.taskanalizator.domain.dtos.NewTicketStatusDTO;
import com.example.taskanalizator.domain.dtos.TicketStatusDTO;
import com.example.taskanalizator.domain.enums.TicketStatus;
import com.example.taskanalizator.mapper.CategoryMapper;
import com.example.taskanalizator.mapper.StatusHistoryMapper;
import com.example.taskanalizator.mapper.TicketMapper;
import com.example.taskanalizator.domain.TicketEntity;
import com.example.taskanalizator.domain.dtos.CreateTicketDTO;
import com.example.taskanalizator.domain.dtos.TicketDTO;
import com.example.taskanalizator.repository.CategoryEntityRepository;
import com.example.taskanalizator.repository.StatusHistoryRepository;
import com.example.taskanalizator.repository.TicketEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.example.taskanalizator.domain.enums.TicketStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketEntityService {

    // Repositories
    private final TicketEntityRepository ticketRepository;
    private final CategoryEntityRepository categoryRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    // Mappers
    private final TicketMapper ticketMapper;
    private final CategoryMapper categoryMapper;
    private final StatusHistoryMapper statusHistoryMapper;

    @Transactional
    public TicketDTO createNewTicket(CreateTicketDTO ticketDTO) {
        log.info("[INFO] CreateTicketDTO - {}", ticketDTO);

//        var categoryEntity = categoryMapper.toEntity(ticketDTO.category());
//        var savedCategory = categoryRepository.save(categoryEntity);

        return ticketMapper.toTicketDTO(
                ticketRepository.save(
                        ticketMapper.toEntity(ticketDTO)
                ), ticketDTO.category(), Collections.emptyList()
        );
    }

    @Transactional
    public List<TicketDTO> findAll() {
        List<TicketEntity> allTickets = ticketRepository.findAll();
        log.info("[INFO] Found {} tickets", allTickets.size());

        if (allTickets.isEmpty()) {
            return Collections.emptyList();
        }

        return allTickets.stream().map(t -> {
            var dto = categoryMapper.toDto(t.getCategory());
            var ticketDTO = ticketMapper.toTicketDTO(t, dto, Collections.emptyList());

            log.info("[INFO] TicketDTO {} before applying history list", ticketDTO);

            var list = t.getHistory()
                    .stream()
                    .map(h -> statusHistoryMapper.toDto(h, ticketDTO))
                    .toList();

            log.info("[INFO] Found {} status history", list.size());

            ticketDTO.statusHistoryDTOS().addAll(
                    list.isEmpty() ? Collections.emptyList() : list
            );

            log.info("[INFO] TicketDTO {} after applying history list", ticketDTO);

            return ticketDTO;
        }).toList();
    }

    @Transactional
    public TicketStatusDTO setStatus(UUID id, NewTicketStatusDTO newStatusDTO) {
        TicketEntity ticketEntity = ticketRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Ticket with id " + id + " not found")
        );

        String oldStatus = ticketEntity.getStatus().toString();

        switch (TicketStatus.fromString(newStatusDTO.status())) {
            case IN_PROGRESS -> {
                if(ticketEntity.getStatus().equals(TicketStatus.NEW)) {
                    ticketEntity.setStatus(IN_PROGRESS);
                } else {
                    throw new IllegalArgumentException("Status " + newStatusDTO.status() + " is not in " + IN_PROGRESS);
                }
            }

            case RESOLVED -> {
                if(ticketEntity.getStatus().equals(IN_PROGRESS)) {
                    ticketEntity.setStatus(RESOLVED);
                } else {
                    throw new IllegalArgumentException("Status " + newStatusDTO.status() + " is not in " + RESOLVED);
                }
            }

            case CANCELLED -> {
                if(ticketEntity.getStatus().equals(TicketStatus.NEW)
                        || ticketEntity.getStatus().equals(IN_PROGRESS)) {
                    ticketEntity.setStatus(CANCELLED);
                } else {
                    throw new IllegalArgumentException("Status " + newStatusDTO.status() + " is not in " + TicketStatus.NEW);
                }
            }
        }

        return new TicketStatusDTO("Ваш статус был успешно изменен", oldStatus, newStatusDTO.status());
    }
}
