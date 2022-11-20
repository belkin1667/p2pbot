package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferConfig {

    @Id
    @NotNull
    @GeneratedValue(generator = "offer_config_seq")
    @SequenceGenerator(name = "offer_config_seq", sequenceName = "offer_config_seq", allocationSize = 100)
    private Long id;
    private String offerName;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToMany
    private List<OfferProperty> properties;
    @Enumerated(value = EnumType.STRING)
    private OfferConfigStatus status;
}
