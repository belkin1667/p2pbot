package com.l2lhackathon.peers.bot.handlers;

import com.pengrad.telegrambot.model.Update;

public interface UpdateHandler {

    void handle(Update update);

    boolean canHandle(Update update);
}
