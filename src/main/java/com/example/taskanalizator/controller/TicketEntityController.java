package com.example.taskanalizator.controller;

import com.example.taskanalizator.domain.dtos.CreateTicketDTO;
import com.example.taskanalizator.domain.dtos.NewTicketStatusDTO;
import com.example.taskanalizator.domain.dtos.TicketDTO;
import com.example.taskanalizator.domain.dtos.TicketStatusDTO;
import com.example.taskanalizator.service.TicketEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@Slf4j
@RequiredArgsConstructor
public class TicketEntityController {

    private final TicketEntityService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> findAll() {
        log.info("[INFO] GET REQUEST - [Finding all ticket entities]");
        return ResponseEntity.ok(
                ticketService.findAll()
        );
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createNewTicket(@RequestBody CreateTicketDTO ticketDTO) {
        log.info("[INFO] POST REQUEST - [Creating new ticket entity]");
        return ResponseEntity.ok(
                ticketService.createNewTicket(ticketDTO)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketStatusDTO> setStatus(@PathVariable UUID id,
                                                     @RequestBody NewTicketStatusDTO newStatus) {
        log.info("[INFO] PUT REQUEST - [Updating status of ticket entity]");
        return ResponseEntity.ok(ticketService.setStatus(id, newStatus));
    }
}
