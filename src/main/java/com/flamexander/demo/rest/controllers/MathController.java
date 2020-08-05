package com.flamexander.demo.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/math")
public class MathController {

    @GetMapping("/sum")
    public String getSum(@RequestParam Integer a, @RequestParam Integer b) {
        try {
            int result = Math.addExact(a, b);
            return String.valueOf(result);
        } catch (ArithmeticException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/multiply")
    public String getMultiplication(@RequestParam Integer a, @RequestParam Integer b) {
        try {
            int result = Math.multiplyExact(a, b);
            return String.valueOf(result);
        } catch (ArithmeticException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
