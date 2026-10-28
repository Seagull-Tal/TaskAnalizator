package com.example.taskanalizator.domain;


import com.example.taskanalizator.domain.converter.PriorityConverter;
import com.example.taskanalizator.domain.converter.TicketStatusConverter;
import com.example.taskanalizator.domain.enums.Priority;
import com.example.taskanalizator.domain.enums.TicketStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Convert(converter = TicketStatusConverter.class)
    private TicketStatus status;

    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "closed_at")
    private Instant closedAt;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("changedAt ASC")
    private List<StatusHistoryEntity> history;

    @PostConstruct
    public void init() {
        if (isNull(history))
            this.history = new ArrayList<>();
    }

}
