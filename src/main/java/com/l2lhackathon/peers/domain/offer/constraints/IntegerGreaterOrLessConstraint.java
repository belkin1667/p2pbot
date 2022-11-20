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
@DiscriminatorValue(ConstraintType.Names.GREATER_OR_LESS_INTEGER)
public class IntegerGreaterOrLessConstraint extends Constraint {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Pair<GreaterOrLessKey, Integer>> values;

    @Override
    public ConstraintType getType() {
        return ConstraintType.GREATER_OR_LESS_INTEGER;
    }

}
