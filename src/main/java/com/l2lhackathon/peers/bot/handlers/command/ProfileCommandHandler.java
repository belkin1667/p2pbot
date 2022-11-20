package com.l2lhackathon.peers.bot.handlers.command;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.bot.controls.BotCommand;
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
public class ProfileCommandHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Ваш профиль: %s";
    private static final String CREATE_MESSAGE = "Привет! Мы пока не знакомы. Хочешь зарегистрироваться?";

    private final BotCommand command = BotCommand.PROFILE;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendButtons(
                chat(update).id(),
                MESSAGE.formatted(user.toBotMessage()),
                List.of(BotButton.EDIT_PROFILE, BotButton.OPEN_PROFILE_WEB_VIEW),
                user.getTelegramId()
        );
    }

    @Override
    public void handleUnauthorized(Update update) {
        bot.sendButtons(
                chat(update).id(),
                CREATE_MESSAGE,
                List.of(BotButton.CREATE_PROFILE, BotButton.CANCEL_UNAUTHORIZED)
        );
    }
}
