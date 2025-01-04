package com.hotel.controllers;

import com.hotel.entities.Ratings;
import com.hotel.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings")
    public ResponseEntity<Ratings> addRating(@RequestBody Ratings rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<Ratings>> getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }

    @PutMapping("/ratings")
    public ResponseEntity<Ratings> updateRating(@RequestBody Ratings rating) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.updateRating(rating));
    }

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<List<Ratings>> getRating(@PathVariable String ratingId) {
        return ResponseEntity.status(200).body(ratingService.findRatingByUserId(ratingId));
    }

    @GetMapping("/ratings/hotel/{hotelId}")
    public ResponseEntity<List<Ratings>> getRatingsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.status(200).body(ratingService.findRatingsByHotelId(hotelId));
    }

    @GetMapping("/ratings/users/{userId}")
    public ResponseEntity<List<Ratings>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.status(200).body(ratingService.findRatingByUserId(userId));
    }
}
