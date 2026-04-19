package com.example.groupmanagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Backend is running!";
    }
}