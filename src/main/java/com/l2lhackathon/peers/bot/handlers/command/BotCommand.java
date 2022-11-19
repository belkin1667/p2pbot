package com.l2lhackathon.peers.bot.handlers.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BotCommand {

    START("/start"),
    PROFILE("/profile"),
    HELP("/help"),
    TEST_WEB_VIEW_BUTTONS("/test_webview");

    private final String command;
}
