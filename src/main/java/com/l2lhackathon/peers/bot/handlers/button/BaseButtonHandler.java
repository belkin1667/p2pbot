package com.l2lhackathon.peers.bot.handlers.button;

import java.util.Optional;

import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

public abstract class BaseButtonHandler implements UpdateHandler {

    abstract BotButton getButton();

    @Override
    public boolean canHandle(Update update) {
        return Optional.ofNullable(update)
                .map(Update::callbackQuery)
                .map(CallbackQuery::data)
                .filter(data -> getButton().getCallback().equals(data))
                .isPresent();
    }

    @Override
    public Long chatId(Update update) {
        return update.callbackQuery().message().chat().id();
    }

    @Override
    public User user(Update update) {
        return update.callbackQuery().message().from();
    }
}
