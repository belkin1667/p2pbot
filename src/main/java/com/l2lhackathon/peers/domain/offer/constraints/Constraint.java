package com.l2lhackathon.peers.domain.offer.constraints;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.l2lhackathon.peers.domain.offer.OfferProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property_constraint")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Constraint {

    @Id
    @NotNull
    @GeneratedValue(generator = "property_constraint_seq")
    @SequenceGenerator(name = "property_constraint_seq", sequenceName = "property_constraint_seq", allocationSize = 100)
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;

    @Enumerated(value = EnumType.STRING)
    public abstract ConstraintType getType();
}
