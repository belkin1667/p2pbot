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
@Table(name = "userglobal")
public class UserGlobalEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "base_id")
    private Long baseId;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "reviews")
    private Long reviews;

}
