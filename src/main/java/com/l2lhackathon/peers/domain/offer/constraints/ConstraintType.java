package com.l2lhackathon.peers.domain.offer.constraints;

public enum ConstraintType {
    SELECTOR_STRING,
    SELECTOR_INTEGER,
    GREATER_OR_LESS_INTEGER,
    GREATER_OR_LESS_DATE,
    NONE;

    public static class Names {

        public static final String SELECTOR_STRING = "SELECTOR_STRING";
        public static final String SELECTOR_INTEGER = "SELECTOR_INTEGER";
        public static final String GREATER_OR_LESS_INTEGER = "GREATER_OR_LESS_INTEGER";
        public static final String GREATER_OR_LESS_DATE = "GREATER_OR_LESS_DATE";
        public static final String NONE = "NONE";

    }
}
