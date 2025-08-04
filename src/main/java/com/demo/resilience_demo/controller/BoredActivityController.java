package com.demo.resilience_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/") // Base path for this controller
public class BoredActivityController {

@Autowired
@Qualifier("myRestTemplate")
RestTemplate restTemplate;

    @GetMapping("/welcome") // Handles GET requests to /activity/random
    public String sayHi() {
    	return restTemplate.getForObject("http://localhost:9090/welcome", String.class); // Make the GET request
    }
    
    @GetMapping("/bye") // Handles GET requests to /activity/random
    public String sayBye() {
    	return restTemplate.getForObject("http://localhost:9090/bye", String.class); // Make the GET request
    }
}

