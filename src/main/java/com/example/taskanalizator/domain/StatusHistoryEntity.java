package com.example.taskanalizator.domain;

import com.example.taskanalizator.domain.converter.TicketStatusConverter;
import com.example.taskanalizator.domain.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "status_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @Convert(converter = TicketStatusConverter.class)
    @Column(name = "from_status", length = 20)
    private TicketStatus fromStatus;

    @Convert(converter = TicketStatusConverter.class)
    @Column(name = "to_status", nullable = false, length = 20)
    private TicketStatus toStatus;

    @Column(name = "changed_at", nullable = false)
    private Instant changedAt;

    @Column(length = 500)
    private String comment;
}
