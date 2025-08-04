package com.demo.resilience_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;

@Configuration
public class RestTemplateConfig {

    @Bean("myRestTemplate")
    public RestTemplate restTemplate(CircuitBreakingInterceptor circuitBreakingInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors == null) {
            interceptors = new ArrayList<>();
        }
        interceptors.add((ClientHttpRequestInterceptor)circuitBreakingInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
    
}
