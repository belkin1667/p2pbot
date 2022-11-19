package com.l2lhackathon.peers.bot.handlers.command;

import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.pengrad.telegrambot.model.Update;

public abstract class BaseCommandHandler implements UpdateHandler {

    abstract BotCommand getCommand();

    @Override
    public boolean canHandle(Update update) {
        String messageText = update.message().text();
        if (messageText == null) {
            return false;
        }
        return messageText.startsWith(getCommand().getCommand());
    }
}
