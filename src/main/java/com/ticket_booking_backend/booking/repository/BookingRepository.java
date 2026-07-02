package com.ticket_booking_backend.booking.repository;

import com.ticket_booking_backend.booking.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    int countByUserIdAndEventId(UUID userId, UUID eventId);
}
