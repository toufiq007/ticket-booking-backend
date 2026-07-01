package com.ticket_booking_backend.booking.repository;

import com.ticket_booking_backend.booking.entities.EventEntity;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    Long countByEventIdAndStatus(UUID eventId, TicketStatus ticketStatus);

    @Query(value = """
    SELECT * FROM tickets
    WHERE event_id = :eventId
    AND status = 'AVAILABLE'
    LIMIT 1
    FOR UPDATE SKIP LOCKED
    """, nativeQuery = true)
    Optional<TicketEntity> findOneAvailableForUpdate(UUID eventId);

}
