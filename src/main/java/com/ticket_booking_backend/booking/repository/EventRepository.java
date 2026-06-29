package com.ticket_booking_backend.booking.repository;

import com.ticket_booking_backend.booking.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findByIsActiveTrue();
}
