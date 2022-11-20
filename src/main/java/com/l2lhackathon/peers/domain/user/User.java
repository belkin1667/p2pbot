package com.l2lhackathon.peers.domain.user;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString(exclude = "dialogStage")
public class User {

    @Id
    @NotNull
    @GeneratedValue(generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 100)
    private Long id;
    @NotNull
    private Instant createdAt;
    @NotNull
    private Instant updatedAt;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String telegramLogin;
    @NotNull
    private Long telegramId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private DialogStage dialogStage;

    private Integer previousMessageId;

    private Long currentOfferId;
    private Integer nextOfferConfigPropertyNumber;

    @Embedded
    private Location location;

    private String photoUrl;

    private BigDecimal rating;

    @NotNull
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsOnMe;
    @NotNull
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> myReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    public void setCity(String city) {
        if (location == null) {
            location = new Location();
        }
        location.setCity(city);
    }

    public void setCountry(String country) {
        if (location == null) {
            location = new Location();
        }
        location.setCountry(country);
    }

    public void init(String firstName,
                     String lastName,
                     String telegramLogin,
                     Long telegramId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telegramLogin = telegramLogin;
        this.telegramId = telegramId;
        reviewsOnMe = new ArrayList<>();
        myReviews = new ArrayList<>();
        dialogStage = DialogStage.UNKNOWN;
        createdAt = Instant.now();
        updatedAt = Instant.now();
        rating = new BigDecimal(new Random().nextInt(1, 4) + "." + new Random().nextInt(0, 9));
    }

    public String toBotMessage() {
        return toString();
    }

    public void decrementNextOfferConfigPropertyNumber() {
        nextOfferConfigPropertyNumber--;
        if (nextOfferConfigPropertyNumber < 0) {
            nextOfferConfigPropertyNumber = 0;
        }
    }

    public void incrementNextOfferConfigPropertyNumber() {
        nextOfferConfigPropertyNumber++;
    }
}
