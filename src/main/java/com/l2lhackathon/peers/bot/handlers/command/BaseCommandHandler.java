package com.l2lhackathon.peers.bot.handlers.command;

import java.util.Optional;

import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

public abstract class BaseCommandHandler implements UpdateHandler {

    abstract BotCommand getCommand();

    @Override
    public boolean canHandle(Update update) {
        return Optional.ofNullable(update)
                .map(Update::message)
                .map(Message::text)
                .filter(text -> text.startsWith(getCommand().getCommand()))
                .isPresent();
    }

    @Override
    public Long chatId(Update update) {
        return update.message().chat().id();
    }

    @Override
    public User user(Update update) {
        return update.message().from();
    }
}
