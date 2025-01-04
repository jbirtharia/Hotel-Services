package com.hotel.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {

    /// Added loadBalanced annotation, so that RestTemplate will picked service name from Eureka, and
    /// will call service through Eureka by service name. So no need to call service through IP and port.
    /// It will call by service name.
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
