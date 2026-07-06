package com.ticket_booking_backend.booking.dto.request;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {

    private UUID id;
    private String eventName;
    private String description;
    private String venue;
    private LocalDateTime eventDate;
    private Integer totalTickets;
    private Long availableTickets;
    private BigDecimal ticketPrice;
    private Boolean isActive;
}
