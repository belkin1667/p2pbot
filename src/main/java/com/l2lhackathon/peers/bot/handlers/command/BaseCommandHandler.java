package com.l2lhackathon.peers.bot.handlers.command;

import java.util.Optional;

import com.l2lhackathon.peers.bot.controls.BotCommand;
import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;

public abstract class BaseCommandHandler extends UpdateHandler {

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
    public Chat chat(Update update) {
        return message(update).chat();
    }

    @Override
    public Message message(Update update) {
        return Optional.ofNullable(update.message()).orElseGet(() -> update.callbackQuery().message());
    }

    @Override
    public User user(Update update) {
        return message(update).from();
    }
}
