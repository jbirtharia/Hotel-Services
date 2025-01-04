package com.hotel.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Ratings {

    private String ratingId;
    private String userId;
    private Long hotelId;
    private int rating;
    private Hotel hotel;

}
