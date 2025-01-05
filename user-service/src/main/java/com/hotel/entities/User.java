package com.hotel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;

    @Column
    private String name;

    @Column
    private String emailId;

    @Transient
    List<Ratings> ratings = new ArrayList<>();
}
