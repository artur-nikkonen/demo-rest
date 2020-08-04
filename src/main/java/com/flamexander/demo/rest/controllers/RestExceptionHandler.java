package com.flamexander.demo.rest.controllers;

import com.flamexander.demo.rest.exceptions.BadRequestException;
import com.flamexander.demo.rest.exceptions.ErrorResponse;
import com.flamexander.demo.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handle404(ResourceNotFoundException e) {
        ErrorResponse err = new ErrorResponse();
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        err.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException e) {
        ErrorResponse err = new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(e.getMessage());
        err.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
