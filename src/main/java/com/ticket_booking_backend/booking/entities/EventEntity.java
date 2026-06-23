package com.ticket_booking_backend.booking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(nullable = false)
    private Integer totalTickets;

    @Column(nullable = false)
    private Integer ticketPrice;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(updatable = false)
    private LocalDateTime createdAT = LocalDateTime.now();


}
