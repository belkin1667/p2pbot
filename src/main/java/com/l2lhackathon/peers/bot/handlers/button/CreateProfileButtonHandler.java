package com.l2lhackathon.peers.bot.handlers.button;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.controller.user.entity.DialogStage;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class CreateProfileButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Профиль создан:\n%s";
    private static final String MESSAGE_ALREADY_EXISTS = "Профиль уже существует";

    @Getter
    private final BotButton button = BotButton.CREATE_PROFILE;
    private final DialogStage dialogStageAfter = DialogStage.PROFILE_CREATED;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendMessage(chat(update).id(), MESSAGE_ALREADY_EXISTS);
    }

    @Override
    public void handleUnauthorized(Update update) {
        var telegramUser = user(update);
        User user = new User();
        user.init(telegramUser.firstName(), telegramUser.lastName(), telegramUser.username(), telegramUser.id());
        userRepository.save(user);
        bot.sendButtons(
                chat(update).id(),
                MESSAGE.formatted(user.toBotMessage()),
                List.of(BotButton.EDIT_PROFILE, BotButton.OPEN_PROFILE_WEB_VIEW),
                user.getTelegramId()
        );
    }
}
