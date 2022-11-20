package com.l2lhackathon.peers.controller.user.entity;

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
    OFFERED_CHOSEN(false),
    PROFILE_CREATED(false),

    UNKNOWN(false);

    private final Boolean deletePrevious;
}
