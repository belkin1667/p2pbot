package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.l2lhackathon.peers.controller.offer.dto.OfferPropertyDto;
import com.l2lhackathon.peers.domain.offer.constraints.Constraint;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
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

    @OneToOne(cascade = {CascadeType.ALL})
    private Constraint constraint;

    @ManyToOne
    private OfferConfig config;

    public void init(OfferPropertyDto propertyDto, OfferConfig config) {
        this.config = config;
        createdAt = Instant.now();
        updatedAt = Instant.now();
        name = propertyDto.getName();
        type = Enum.valueOf(OfferPropertyType.class, propertyDto.getType().name());
        var constraintType = Enum.valueOf(ConstraintType.class, propertyDto.getConstraint().getType().name());
        constraint = constraintType.getProducer().apply(propertyDto.getConstraint());
    }
}
