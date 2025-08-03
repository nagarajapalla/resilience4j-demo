package com.demo.resilience_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/activity") // Base path for this controller
public class BoredActivityController {

@Autowired
@Qualifier("myRestTemplate")
RestTemplate restTemplate;

    @GetMapping("/random") // Handles GET requests to /activity/random
    public String getRandomBoredActivity() {
    	return restTemplate.getForObject("http://localhost:9090/welcome", String.class); // Make the GET request
    }
}

