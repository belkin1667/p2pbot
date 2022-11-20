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
public class Offer {
    @Id
    @NotNull
    @GeneratedValue(generator = "offer_seq")
    @SequenceGenerator(name = "offer_seq", sequenceName = "offer_seq", allocationSize = 100)
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
