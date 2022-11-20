package com.l2lhackathon.peers.bot.handlers.button;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum BotButton {
    I_AM_SEARCHER("Я ищу", "searcher_btn"),
    I_AM_OFFERER("Я предлагаю", "offerer_btn"),

    EDIT_PROFILE("Редактировать профиль", "edit_profile_btn"),
    EDIT_FIRST_NAME("Имя", "edit_first_name_btn"),
    EDIT_LAST_NAME("Фамилия", "edit_last_name_btn"),
    EDIT_CITY("Город", "edit_city_btn"),
    EDIT_COUNTRY("Страна", "edit_country_btn"),
    CLOSE_EDIT_PROFILE_DIALOG("Отмена", "close_edit_profile_dialog_btn");


    private final String text;
    private final String callback;
}
