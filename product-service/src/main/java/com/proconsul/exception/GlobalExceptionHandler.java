package com.proconsul.exception;

import com.proconsul.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Message buildErrorMessage(HttpStatus status, String message) {
        return new Message(LocalDate.now(),message,status.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Message handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
    }


}
