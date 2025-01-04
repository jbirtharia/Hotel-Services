package com.hotel.healthCheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Perform health checks (e.g., DB connection, external API availability)
        boolean isHealthy = performHealthCheck();

        if (isHealthy) {
            return Health.up().withDetail("CustomService", "Service is running").build();
        } else {
            return Health.down().withDetail("CustomService", "Service is not available").build();
        }
    }

    private boolean performHealthCheck() {
        // Custom logic for health check
        return true; // Assume healthy for this example
    }
}
