package com.l2lhackathon.peers.bot.handlers;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

public interface UpdateHandler {

    void handle(Update update);

    boolean canHandle(Update update);

    Long chatId(Update update);

    User user(Update update);

}
