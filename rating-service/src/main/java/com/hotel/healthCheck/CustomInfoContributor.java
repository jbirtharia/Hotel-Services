package com.hotel.healthCheck;

import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    private final HealthEndpoint healthEndpoint;

    public CustomInfoContributor(HealthEndpoint healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @Override
    public void contribute(Info.Builder builder) {
        // Add health status
        builder.withDetail("health", healthEndpoint.health().getStatus());
        builder.withDetail("app", Map.of(
                "name", "Rating Service",
                "description", "This is a Rating Service Running.",
                "version", "1.0.0"
        ));
    }
}
