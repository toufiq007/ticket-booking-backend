package com.ticket_booking_backend.booking.controllers;

import com.ticket_booking_backend.booking.dto.request.EventCreatDto;
import com.ticket_booking_backend.booking.dto.request.EventDto;
import com.ticket_booking_backend.booking.entities.EventEntity;
import com.ticket_booking_backend.booking.services.EventServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class EventController {

    private final EventServices eventServices;

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> findAllEvents() {
        return ResponseEntity.ok(eventServices.getAllActiveEvents());
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDto> findEventById(@PathVariable UUID id){
        return ResponseEntity.ok(eventServices.getEventById(id));
    }

    @PostMapping("/admin/event")
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventCreatDto eventDto){
        EventDto createdEvent = eventServices.createEvent(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
}
