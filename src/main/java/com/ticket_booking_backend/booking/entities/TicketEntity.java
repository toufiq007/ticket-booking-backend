package com.ticket_booking_backend.booking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="tickets")
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.AVAILABLE;

    @Column(name = "held_by", nullable = true)
    private UUID heldBy;

    @Column(name = "held_time", nullable = true)
    private LocalDateTime heldTime;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
