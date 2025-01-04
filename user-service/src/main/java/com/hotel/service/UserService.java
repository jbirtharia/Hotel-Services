package com.hotel.service;

import com.hotel.apicalls.RestAPICall;
import com.hotel.entities.Hotel;
import com.hotel.entities.Ratings;
import com.hotel.entities.User;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.external_services.HotelService;
import com.hotel.external_services.RatingService;
import com.hotel.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private static final String RATING_URL = "http://RATING-SERVICE/ratings/users/";
    private static final String HOTEL_URL = "http://HOTEL-SERVICE/hotels/";

    @Autowired
    private RestAPICall restAPICall;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        log.info("User Entity : {}", user);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
//        List<Ratings> ratingsOfUser = restAPICall.callAPIWithList(RATING_URL + id, HttpMethod.GET,
//                new ParameterizedTypeReference<List<Ratings>>() {});
        /// Calling Rating API by using Feign Client
        List<Ratings> ratingsOfUser = ratingService.getRatingByUserId(id);
        log.info("Api response of ratings : {}", ratingsOfUser);
        List<Ratings> ratingsOfUserWithHotel = fetchHotelDetails(ratingsOfUser);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not Found : " + id));
        user.setRatings(ratingsOfUserWithHotel);
        return user;
    }

    private List<Ratings> fetchHotelDetails(List<Ratings> ratingsOfUser) {
        if (!ratingsOfUser.isEmpty()){
            ratingsOfUser.forEach(rating -> {
//                Hotel hotel = restAPICall.callAPI(HOTEL_URL + rating.getHotelId(), HttpMethod.GET,
//                        new ParameterizedTypeReference<Hotel>() {});
                /// Calling Hotel API by using Feign Client
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                log.info("Hotel : {}", hotel);
                rating.setHotel(hotel);
            });
        }
        return ratingsOfUser;
    }
}
