package com.ticket_booking_backend.booking.services;

import com.ticket_booking_backend.booking.entities.BookingEntity;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import com.ticket_booking_backend.booking.exceptions.ConflictException;
import com.ticket_booking_backend.booking.exceptions.ResourceNotFoundException;
import com.ticket_booking_backend.booking.repository.BookingRepository;
import com.ticket_booking_backend.booking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServices {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public String confirmTicketBooking(UUID userId) {
        // first find the ticket and change the status
        TicketEntity ticket = ticketRepository.findByHeldByAndStatus(userId,TicketStatus.HELD).orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));
        ticket.setStatus(TicketStatus.SOLD);
        ticketRepository.save(ticket);

        int alreadyBooked = bookingRepository.countByUserIdAndEventId(userId,ticket.getEvent().getId());

        if (alreadyBooked >= 4) {
            throw new ConflictException("Maximum 4 tickets allowed per event per user");
        }

        // now creating the booking entity
        BookingEntity booking = new BookingEntity();
        booking.setUserId(userId);
        booking.setTicket(ticket);
        booking.setEvent(ticket.getEvent());
        booking.setAmountPaid(ticket.getEvent().getTicketPrice());
        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);
        String successMessage = "Ticket confirmed successfully!";
        log.info("Booking confirmed for userId: {}", userId);

        return successMessage;
    }

}
