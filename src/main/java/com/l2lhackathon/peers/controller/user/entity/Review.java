package com.l2lhackathon.peers.controller.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private Long id;
    @ManyToOne
    private User user;
    private String text;
    @ManyToOne
    private User author;

}
