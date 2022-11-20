package com.l2lhackathon.peers.bot.handlers.button;

import java.util.Arrays;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum BotButton {
    I_AM_SEARCHER("Я ищу", "searcher_btn", false),
    I_AM_OFFERER("Я предлагаю", "offerer_btn", false),


    CREATE_PROFILE("Да, создать профиль!", "create_profile_btn", false),
    EDIT_PROFILE("Редактировать профиль", "edit_profile_btn", false),
    EDIT_FIRST_NAME("Имя", "edit_first_name_btn", false),
    EDIT_LAST_NAME("Фамилия", "edit_last_name_btn", false),
    EDIT_CITY("Город", "edit_city_btn", false),
    EDIT_COUNTRY("Страна", "edit_country_btn", false),
    DELETE_PROFILE("Удалить профиль!", "delete_profile_btn", false),
    CLOSE_EDIT_PROFILE_DIALOG("Отмена", "close_edit_profile_dialog_btn", false),
    OPEN_PROFILE_WEB_VIEW("Открыть профиль", "open_profile_web_view", true),

    CANCEL_UNAUTHORIZED("Нет, вернусь позже", "cancel_unauthorized_btn", false);


    private final String text;
    private final String callback;
    private final Boolean webViewEnabled;

    public static Optional<BotButton> findByCallbackData(String callback) {
        return Arrays.stream(values()).filter(btn -> btn.getCallback().equals(callback)).findAny();
    }
}
