package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.l2lhackathon.peers.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "offerElements", "user", "config" })
public class Offer {
    @Id
    @NotNull
    @GeneratedValue(generator = "offer_seq")
    @SequenceGenerator(name = "offer_seq", sequenceName = "offer_seq", allocationSize = 100)
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne
    private OfferConfig config;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferElement> offerElements;

    public void init(User user, OfferConfig selectedOfferConfig) {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        config = selectedOfferConfig;
        this.user = user;
        offerElements = new ArrayList<>();
    }
}
