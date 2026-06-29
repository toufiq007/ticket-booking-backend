package com.ticket_booking_backend.booking.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCreatDto {

    @NotBlank(message = "event name is required")
    private String name;

    private String description;

    @NotNull(message = "event date is required")
    private LocalDateTime eventDate;

    private String venue;

    @NotNull
    @Min(value = 1, message = "Must have at least 1 ticket")
    private Integer totalTickets;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal ticketPrice;
}
