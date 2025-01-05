package com.hotel.controllers;

import com.hotel.entities.User;
import com.hotel.fallbackhandler.RatingHotelFallbackHandler;
import com.hotel.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingHotelFallbackHandler ratingHotelFallbackHandler;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/users/{userId}")
    /// Circuit breaker for calling Ratings Service
    /// Need to pass method name in fallback which will send default response
    /// when the dependent services will be down.
    //@CircuitBreaker(name = "ratingsHotelsCircuitBreaker", fallbackMethod = "getRatingHotelsFallback")
    /// Implemented retry which will attempt to call below method 3 times if dependent service seems down.
    @Retry(name = "retryForRatingsHotelsServices", fallbackMethod = "getRatingHotelsFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    /// Delegate fallback to the external class
    /// Fallback method must have the same parameters and same return type
    /// as the original method.
    /// Additionally, the fallback method can accept an optional Throwable parameter
    /// (as the last parameter) to handle the exception that triggered the fallback.
    public ResponseEntity<User> getRatingHotelsFallback(String userId, Throwable throwable) {
        return ratingHotelFallbackHandler.fallbackForServiceCall(userId, throwable);
    }
}
