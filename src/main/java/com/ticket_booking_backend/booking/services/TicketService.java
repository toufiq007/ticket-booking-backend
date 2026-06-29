package com.ticket_booking_backend.booking.services;

import com.ticket_booking_backend.booking.dto.request.TicketCountDto;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import com.ticket_booking_backend.booking.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketCountDto getTicketCountDto(UUID eventId) {
        Long count = ticketRepository.countByEventIdAndStatus(eventId, TicketStatus.AVAILABLE);
        return new TicketCountDto(count);
    }

}
