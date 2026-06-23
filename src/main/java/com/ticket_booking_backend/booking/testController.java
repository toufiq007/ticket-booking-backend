package com.ticket_booking_backend.booking;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/home")
    public String home() {
        return "Hello World!";
    }
}
