package com.flamexander.demo.rest.exceptions;

public class ClientNotFoundException extends ResourceNotFoundException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
