package com.l2lhackathon.peers.domain.offer;


import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.l2lhackathon.peers.domain.offer.constraints.Constraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferProperty {
    @Id
    @NotNull
    @GeneratedValue(generator = "offer_property_seq")
    @SequenceGenerator(name = "offer_property_seq", sequenceName = "offer_property_seq", allocationSize = 100)
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private OfferPropertyType type;

    @OneToOne
    private Constraint constraints;

    @ManyToOne
    private OfferConfig config;
}
