package com.mamarek123.infrastructure.error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    ResponseEntity<String> handleJPAViolations(ConstraintViolationException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}