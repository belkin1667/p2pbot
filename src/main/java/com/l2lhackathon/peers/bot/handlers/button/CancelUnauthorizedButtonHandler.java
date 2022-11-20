package com.l2lhackathon.peers.bot.handlers.button;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
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
public class CancelUnauthorizedButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Скорее возвращайся!";

    private final BotButton button = BotButton.CANCEL_UNAUTHORIZED;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendMessage(chat(update).id(), MESSAGE);
    }

    @Override
    public void handleUnauthorized(Update update) {
        bot.sendMessage(chat(update).id(), MESSAGE);
    }
}
