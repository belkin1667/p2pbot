package com.l2lhackathon.peers.domain.offer.constraints;

import java.util.function.Function;

import com.l2lhackathon.peers.controller.offer.dto.constraints.ConstraintDto;
import com.l2lhackathon.peers.controller.offer.dto.constraints.IntegerGreaterOrLessConstraintDto;
import com.l2lhackathon.peers.controller.offer.dto.constraints.StringSelectorConstraintDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConstraintType {
    SELECTOR_STRING(dto -> {
        StringSelectorConstraintDto casted = (StringSelectorConstraintDto) dto;
        StringSelectorConstraint constraint = new StringSelectorConstraint();
        constraint.init(casted);
        return constraint;
    }),
    SELECTOR_INTEGER(dto -> null),
    GREATER_OR_LESS_INTEGER(dto -> {
        IntegerGreaterOrLessConstraintDto casted = (IntegerGreaterOrLessConstraintDto) dto;
        IntegerGreaterOrLessConstraint constraint = new IntegerGreaterOrLessConstraint();
        constraint.init(casted);
        return constraint;
    }),
    GREATER_OR_LESS_DATE(dto -> null),
    NONE(dto -> null);

    private final Function<ConstraintDto, ? extends Constraint> producer;

    public static class Names {

        public static final String SELECTOR_STRING = "SELECTOR_STRING";
        public static final String SELECTOR_INTEGER = "SELECTOR_INTEGER";
        public static final String GREATER_OR_LESS_INTEGER = "GREATER_OR_LESS_INTEGER";
        public static final String GREATER_OR_LESS_DATE = "GREATER_OR_LESS_DATE";
        public static final String NONE = "NONE";

    }
}
