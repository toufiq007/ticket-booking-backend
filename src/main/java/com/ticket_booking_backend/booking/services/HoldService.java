package com.ticket_booking_backend.booking.services;


import com.ticket_booking_backend.booking.dto.response.HeldResponseDto;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import com.ticket_booking_backend.booking.exceptions.ConflictException;
import com.ticket_booking_backend.booking.exceptions.ResourceNotFoundException;
import com.ticket_booking_backend.booking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HoldService {

    private final TicketRepository ticketRepository;

    @Transactional
    public HeldResponseDto holdAndLockTheTicket( UUID eventId, UUID userId) {
        // 1: find the ticket
        TicketEntity ticket = ticketRepository.findOneAvailableForUpdate(eventId).orElseThrow(()-> new ConflictException("Ticket not found in this event. Event id" + eventId));
        boolean alreadyHolding = ticketRepository.existsByHeldByAndStatus(userId, TicketStatus.HELD);

        if (alreadyHolding) {
            throw new ConflictException("You already have a ticket on hold. Complete payment or wait for it to expire.");
        }

        // 3: status held, held-by set korte hobe and heldUntil set korte hobe
        ticket.setHeldBy(userId);
        ticket.setStatus(TicketStatus.HELD);
        ticket.setHeldTime(LocalDateTime.now().plusMinutes(2));


        // 4: save korte hobe
        ticketRepository.save(ticket);

        // HeldResponseDto response send back
        HeldResponseDto heldResponse = new HeldResponseDto();
        heldResponse.setTicketId(ticket.getId());
        heldResponse.setExpiresAt(ticket.getHeldTime());

        return heldResponse;

    }


    @Transactional
    public Boolean releaseTicket( UUID userId) {
        TicketEntity ticket = ticketRepository.findByHeldByAndStatus(userId,TicketStatus.HELD).orElseThrow(()-> new ResourceNotFoundException("Ticket not found from this user. userId" + userId   ));
        // update the status available
        ticket.setHeldBy(null);
        ticket.setHeldTime(null);
        ticket.setStatus(TicketStatus.AVAILABLE);

        // save to db
        ticketRepository.save(ticket);

        // success response
        return true;
    }
}
