package com.ticket_booking_backend.booking.controllers;


import com.ticket_booking_backend.booking.dto.request.TicketCountDto;
import com.ticket_booking_backend.booking.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/available-count")
    public ResponseEntity<TicketCountDto> getAvailableCount(@RequestParam UUID eventId) {
        return ResponseEntity.ok(ticketService.getTicketCountDto(eventId));
    }
}
