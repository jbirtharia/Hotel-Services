package com.hotel.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Hotel {

    private Long id;
    private String name;
    private String location;
}
