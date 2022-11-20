package com.l2lhackathon.peers.bot.handlers.button.selector;

import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.domain.user.User;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public abstract class SelectorHandler {

    public abstract SelectorType getType();
    public abstract void handle(Update update, User user);

    public Chat chat(Update update) {
        return message(update).chat();
    }

    public Message message(Update update) {
        return update.callbackQuery().message();
    }

    public com.pengrad.telegrambot.model.User user(Update update) {
        return update.callbackQuery().from();
    }

}
