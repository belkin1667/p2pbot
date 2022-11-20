package com.l2lhackathon.peers.domain.offer.constraints;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.l2lhackathon.peers.controller.offer.dto.constraints.GreaterOrLessKeyDto;
import com.l2lhackathon.peers.controller.offer.dto.constraints.IntegerGreaterOrLessConstraintDto;
import com.l2lhackathon.peers.controller.offer.dto.constraints.PairDto;
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

    public void init(IntegerGreaterOrLessConstraintDto dto) {
        setCreatedAt(Instant.now());
        setUpdatedAt(Instant.now());
        var list = dto.getValues()
                .stream()
                .map(this::convert)
                .toList();
        setValues(new ArrayList<>(list));
    }

    private Pair<GreaterOrLessKey, Integer> convert(PairDto<GreaterOrLessKeyDto, Integer> pairDto) {
        var key = Enum.valueOf(GreaterOrLessKey.class, pairDto.getKey().name());
        var value = pairDto.getValue();
        return Pair.<GreaterOrLessKey, Integer>builder().key(key).value(value).build();
    }
}
