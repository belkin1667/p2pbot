package com.l2lhackathon.peers.bot.handlers.button;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Button {
    I_AM_SEARCHER("Я ищу", "searcher_btn"),
    I_AM_OFFERER("Я предлагаю", "offerer_btn");

    private final String text;
    private final String callback;
}
