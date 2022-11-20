package com.l2lhackathon.peers.domain.offer.constraints;

import java.util.Comparator;
import java.util.function.BiFunction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GreaterOrLessKey {

    GREATER((Integer a, Integer b) -> Comparator.<Integer>naturalOrder().compare(a, b) > 0, "больше"),
    GREATER_OR_EQUAL((Integer a, Integer b) -> Comparator.<Integer>naturalOrder().compare(a, b) >= 0, "больше или равно"),
    LESS((Integer a, Integer b) -> Comparator.<Integer>naturalOrder().compare(a, b) < 0, "меньше"),
    LESS_OR_EQUAL((Integer a, Integer b) -> Comparator.<Integer>naturalOrder().compare(a, b) <= 0, "меньше или равно"),
    EQUAL((Integer a, Integer b) -> Comparator.<Integer>naturalOrder().compare(a, b) == 0, "равно");

    private final BiFunction<Integer, Integer, Boolean> comparator;
    private final String readableName;
}
