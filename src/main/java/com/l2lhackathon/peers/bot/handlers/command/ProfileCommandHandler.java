package com.l2lhackathon.peers.bot.handlers.command;

import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class ProfileCommandHandler extends BaseCommandHandler {

    @Getter
    private final BotCommand command = BotCommand.PROFILE;

    @Override
    public void handle(Update update) {

    }
}
