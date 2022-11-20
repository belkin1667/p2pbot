package com.l2lhackathon.peers.domain.offer;

import java.time.LocalDate;
import java.util.function.Function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OfferPropertyType {

    STRING(String.class, String::toString),
    INTEGER(Integer.class, Integer::getInteger),
    DATE(LocalDate.class, LocalDate::parse);

    private final Class<?> javaClazz;
    private final Function<String, Object> parser;
}
