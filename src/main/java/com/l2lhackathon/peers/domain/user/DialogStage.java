package com.l2lhackathon.peers.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DialogStage {
    EDIT_LAST_NAME(true),
    EDIT_FIRST_NAME(true),
    EDIT_COUNTRY(true),
    EDIT_CITY(true),

    SEARCHER_CHOSEN(false),
    OFFERER_CHOSEN(false),
    PROFILE_CREATED(false),

    UNKNOWN(false),

    STRING_SELECTOR_AWAITING(false),
    INTEGER_AWAITING(true),
    OFFER_CONFIG_CHOSEN(false);

    private final Boolean deletePrevious;
}
