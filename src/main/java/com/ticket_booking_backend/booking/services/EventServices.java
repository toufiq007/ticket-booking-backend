package com.ticket_booking_backend.booking.services;

import com.ticket_booking_backend.booking.dto.request.EventCreatDto;
import com.ticket_booking_backend.booking.dto.request.EventDto;
import com.ticket_booking_backend.booking.entities.EventEntity;
import com.ticket_booking_backend.booking.entities.TicketEntity;
import com.ticket_booking_backend.booking.entities.TicketStatus;
import com.ticket_booking_backend.booking.exceptions.ResourceNotFoundException;
import com.ticket_booking_backend.booking.repository.EventRepository;
import com.ticket_booking_backend.booking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServices {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;

    public List<EventDto> getAllActiveEvents (){
        return eventRepository.findByIsActiveTrue()
                .stream()
                .map(event -> {
                    EventDto dto = modelMapper.map(event, EventDto.class);
                    Long availableTickets = ticketRepository.countByEventIdAndStatus(event.getId(), TicketStatus.AVAILABLE);
                    dto.setAvailableTickets(availableTickets);
                    return dto;
                })
//                .map(event-> modelMapper.map(event, EventDto.class))
                .toList();
    }

    public EventDto getEventById(UUID id){
        EventEntity eventEntity = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found with this id " + id));
        Long ticketCount = ticketRepository.countByEventIdAndStatus(id,TicketStatus.AVAILABLE);
        EventDto event = modelMapper.map(eventEntity, EventDto.class);
        event.setAvailableTickets(ticketCount);
        return event;
    }


    @Transactional
    public EventDto createEvent(EventCreatDto eventDto){
        EventEntity event = EventEntity.builder()
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .eventDate(eventDto.getEventDate())
                .totalTickets(eventDto.getTotalTickets())
                .ticketPrice(eventDto.getTicketPrice())
                .venueName(eventDto.getVenue())
                .isActive(true)
                .createdAT(LocalDateTime.now())
                .build();

        EventEntity savedEntity =  eventRepository.save(event);

//        auto create tickets for this event
        List<TicketEntity> tickets = new ArrayList<>();
        for (int i=0; i<eventDto.getTotalTickets(); i++){
            tickets.add(
                    TicketEntity
                            .builder()
                            .event(savedEntity)
                            .status(TicketStatus.AVAILABLE)
                            .build());
        }

        ticketRepository.saveAll(tickets);
        log.info("Event '{}' created with {} tickets", savedEntity.getName(), eventDto.getTotalTickets());
        return modelMapper.map(savedEntity,EventDto.class );

    }




}
