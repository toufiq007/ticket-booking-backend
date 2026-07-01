package com.ticket_booking_backend.booking.scheduler;


import com.ticket_booking_backend.booking.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketCleanUp {
    private final TicketRepository ticketRepository;

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void releaseExpiresAndHold() {
        int released = ticketRepository.releaseExpiredHolds(LocalDateTime.now());
        if (released > 0) {
            log.info("Released {} expired held tickets back to AVAILABLE", released);
        }
    }
}
