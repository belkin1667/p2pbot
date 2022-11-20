package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.l2lhackathon.peers.controller.offer.dto.OfferConfigDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"properties", "offers"})
public class OfferConfig {

    @Id
    @NotNull
    @GeneratedValue(generator = "offer_config_seq")
    @SequenceGenerator(name = "offer_config_seq", sequenceName = "offer_config_seq", allocationSize = 100)
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToMany(mappedBy = "config", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferProperty> properties;
    @Enumerated(value = EnumType.STRING)
    private OfferConfigStatus status;

    @OneToMany(mappedBy = "config", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    public void init(OfferConfigDto config) {
        name = config.getOfferName();
        status = OfferConfigStatus.ACTIVE;
        createdAt = Instant.now();
        updatedAt = Instant.now();
        properties = new ArrayList<>();
        config.getProperties().forEach(propertyDto -> {
            OfferProperty property = new OfferProperty();
            property.init(propertyDto, this);
            properties.add(property);
        });
    }
}
