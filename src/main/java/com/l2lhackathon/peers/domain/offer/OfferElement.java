package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "offer", "property" })
public class OfferElement {
    @Id
    @NotNull
    @GeneratedValue(generator = "offer_element_seq")
    @SequenceGenerator(name = "offer_element_seq", sequenceName = "offer_element_seq", allocationSize = 100)
    private Long id;
    private String value;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne
    private Offer offer;
    @ManyToOne
    private OfferProperty property;

    public void init(String value, Offer offer, OfferProperty property) {
        this.value = value;
        createdAt = Instant.now();
        updatedAt = Instant.now();
        this.offer = offer;
        this.property = property;
    }
}
