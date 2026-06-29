package com.ticket_booking_backend.booking.repository;

import com.ticket_booking_backend.booking.entities.EventEntity;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    Long countByEventIdAndStatus(UUID eventId, TicketStatus ticketStatus);
}
