package com.allermedia.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Handles the exceptions globally in this microservice */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApiProcessingException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorDetails> handleProcessingError(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
