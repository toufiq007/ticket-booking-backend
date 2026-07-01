package com.ticket_booking_backend.booking.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeldResponseDto {
    private UUID ticketId;
    private LocalDateTime expiresAt;
}
