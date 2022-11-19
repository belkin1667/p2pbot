package com.l2lhackathon.peers.domain;


import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 100)
    private Long id;

    private Instant createdAt;
    private Instant updatedAt;

    private String telegramLogin;

    @Embedded
    private Location location;

    private String photoUrl;

    private BigDecimal rating;

}
