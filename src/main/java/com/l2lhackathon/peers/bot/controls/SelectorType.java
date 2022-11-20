package com.l2lhackathon.peers.bot.controls;

import java.util.Arrays;
import java.util.Optional;

public enum SelectorType {
    OFFER_TYPE,
    PROPERTY_VALUE;

    public String getEffectiveCallbackFrom(String callback) {
        return callback.substring(name().length() + 1);
    }

    public static Optional<SelectorType> from(String callback) {
        return Arrays.stream(SelectorType.values()).filter(val -> callback.startsWith(val.name() + ":")).findAny();
    }
}
