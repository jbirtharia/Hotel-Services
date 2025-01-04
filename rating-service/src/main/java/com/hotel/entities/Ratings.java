package com.hotel.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document("ratings")
public class Ratings {

    @Id
    private String ratingId;
    private String userId;
    private Long hotelId;
    private int rating;

}
