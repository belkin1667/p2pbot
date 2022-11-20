package com.l2lhackathon.peers.domain.offer;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferElement {
    @Id
    @NotNull
    @GeneratedValue(generator = "offer_element_seq")
    @SequenceGenerator(name = "offer_element_seq", sequenceName = "offer_element_seq", allocationSize = 100)
    private Long id;
    private String value;
    private Instant createdAt;
    private Instant updatedAt;
}
