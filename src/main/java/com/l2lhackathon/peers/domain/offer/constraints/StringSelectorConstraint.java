package com.l2lhackathon.peers.domain.offer.constraints;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@Setter(AccessLevel.PROTECTED)
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@DiscriminatorValue(ConstraintType.Names.SELECTOR_STRING)
public class StringSelectorConstraint extends Constraint {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<String> values;

    @Override
    public ConstraintType getType() {
        return ConstraintType.SELECTOR_STRING;
    }

}
