package com.ticket_booking_backend.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound (ResourceNotFoundException ex){
        return builderResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> conflict(ConflictException ex){
        return builderResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,String> fieldsError = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((field)->{
            fieldsError.put(field.getField(),field.getDefaultMessage());
        });

        Map<String,Object> body = new HashMap<>();
        body.put("status","400");
        body.put("message", "validation error");
        body.put("fields-error",fieldsError);
        body.put("timestamp",LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

    private ResponseEntity<Map<String, Object>> builderResponse(HttpStatus status, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("message", message);
        response.put("timeStamp", LocalDateTime.now());

        return ResponseEntity.status(status).body(response);
    }
}
