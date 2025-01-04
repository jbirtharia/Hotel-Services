package com.hotel.service;

import com.hotel.entities.Ratings;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Ratings saveRating(Ratings rating) {
        return ratingRepository.save(rating);
    }

    public Ratings getRatingById(String ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Id not Found : " + ratingId));
    }

    public List<Ratings> getAllRatings() { return ratingRepository.findAll(); }

    public List<Ratings> findRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Ratings> findRatingsByHotelId(Long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    public Ratings updateRating(Ratings rating) {
        if(ratingRepository.findById(rating.getRatingId()).isPresent()) {
            return ratingRepository.save(rating);
        } else {
            throw new ResourceNotFoundException("Id not Found : " + rating.getRatingId());
        }
    }
}
