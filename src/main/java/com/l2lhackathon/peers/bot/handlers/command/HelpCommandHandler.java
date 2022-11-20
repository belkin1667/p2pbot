package com.l2lhackathon.peers.bot.handlers.command;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.controller.user.entity.DialogStage;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class HelpCommandHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Доступные команды:\n\n/start\n/profile\n/help";

    private final BotCommand command = BotCommand.HELP;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendMessage(chat(update).id(), MESSAGE);
    }

    @Override
    public void handleUnauthorized(Update update) {
        bot.sendMessage(chat(update).id(), MESSAGE);
    }
}
