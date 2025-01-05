package com.hotel.fallbackhandler;

import com.hotel.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RatingHotelFallbackHandler {

    public ResponseEntity<User> fallbackForServiceCall(String userId, Throwable throwable) {
        log.error("Fallback has been executed because getting error " +
                "while calling Rating or Hotel service : {}", throwable.getMessage());
        User user = User.builder()
                .userId(userId)
                .name("dummy")
                .emailId("dummy")
                .ratings(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
    }
}
