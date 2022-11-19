package com.l2lhackathon.peers.controller.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String firstName;
    private String lastName;
    private String telegramLogin;

    @Embedded
    private Location location;

    private String photoUrl;

    private BigDecimal rating;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsOnMe;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> myReviews;

}
