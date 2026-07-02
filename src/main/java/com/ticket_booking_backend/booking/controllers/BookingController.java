package com.ticket_booking_backend.booking.controllers;
import com.ticket_booking_backend.booking.services.BookingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingServices bookingServices;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmTicket (@RequestParam UUID userId) {
        String successMessage = bookingServices.confirmTicketBooking(userId);
        return ResponseEntity.ok(successMessage);
    }
}
