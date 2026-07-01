package com.ticket_booking_backend.booking.controllers;
import com.ticket_booking_backend.booking.dto.response.HeldResponseDto;
import com.ticket_booking_backend.booking.services.HoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HoldController {
    private final HoldService holdService;

    @PostMapping("/tickets/hold")
    public ResponseEntity<?> holdTicket(@RequestParam UUID eventId, @RequestParam UUID userId) {
       HeldResponseDto heldTicketStatus =  holdService.holdAndLockTheTicket(eventId, userId);
       return ResponseEntity.ok(heldTicketStatus);
    }

    @PostMapping("/tickets/release")
    public ResponseEntity<?> releaseTicket(@RequestParam UUID userId) {
        boolean message = holdService.releaseTicket(userId);
        return ResponseEntity.ok(message);
    }

}
