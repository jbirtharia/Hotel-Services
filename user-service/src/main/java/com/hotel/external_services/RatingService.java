package com.hotel.external_services;

import com.hotel.entities.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService{

    @GetMapping("/ratings/users/{userId}")
    List<Ratings> getRatingByUserId(@PathVariable String userId);

    @PostMapping("/ratings")
    Ratings createRating(Ratings rating);

    @PutMapping("/ratings")
    Ratings updateRating(Ratings rating);
}
