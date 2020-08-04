package com.flamexander.demo.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // <- На вебинаре проблема была в том, что я прописывал @Controller вместо @RestController.
@RequestMapping("/api/v1/math")
public class MathController {

    @GetMapping
    public String getSum(@RequestParam Integer a, @RequestParam Integer b) {
        return String.valueOf(a + b);
    }

}
