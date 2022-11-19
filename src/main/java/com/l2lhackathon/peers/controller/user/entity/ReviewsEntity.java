package com.l2lhackathon.peers.controller.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class ReviewsEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "text")
    private String text;
    @Column(name = "author_id")
    private Long authorid;

}
