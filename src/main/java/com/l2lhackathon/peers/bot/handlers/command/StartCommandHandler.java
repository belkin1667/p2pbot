package com.l2lhackathon.peers.bot.handlers.command;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.handlers.button.BotButton;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class StartCommandHandler extends BaseCommandHandler {

    private static final String BASE_FLOW_MESSAGE = "Выбери, что ты хочешь сделать:";

    private final BotCommand command = BotCommand.START;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;
    private final ProfileCommandHandler profileCommandHandler;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendButtons(
                chat(update).id(),
                BASE_FLOW_MESSAGE,
                List.of(BotButton.I_AM_SEARCHER, BotButton.I_AM_OFFERER)
        );
    }

    @Override
    public void handleUnauthorized(Update update) {
        profileCommandHandler.handle(update);
    }
}
