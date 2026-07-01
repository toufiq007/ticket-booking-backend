package com.ticket_booking_backend.booking.dto.request;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HoldDataDto {

    private UUID eventId;
    private UUID userId;
}
