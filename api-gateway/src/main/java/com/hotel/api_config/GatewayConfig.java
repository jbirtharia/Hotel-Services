package com.hotel.api_config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route to User Service
                .route("user-service", r -> r.path("/users/**")
                        .uri("lb://USER-SERVICE")) // Load Balancer URI

                // Route to Hotel Service
                .route("hotel-service", r -> r.path("/hotels/**")
                        .uri("lb://HOTEL-SERVICE")) // Load Balancer URI

                // Route to Rating Service
                .route("rating-service", r -> r.path("/ratings/**")
                        .uri("lb://RATING-SERVICE")) // Load Balancer URI

                .build();
    }
}
