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
public class CloseEditProfileDialogButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Хорошо!";
    private static final String UNKNOWN_USER_MESSAGE = "Я тебя не знаю! Используй команду /profile, чтобы создать профиль!";

    @Getter
    private final BotButton button = BotButton.CLOSE_EDIT_PROFILE_DIALOG;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handle(Update update) {
        var maybeUser = userRepository.findByTelegramId(user(update).id());
        if (maybeUser.isEmpty()) {
            bot.sendMessage(chatId(update), UNKNOWN_USER_MESSAGE);
            return;
        }
        User user = maybeUser.get();
        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);

        bot.sendMessage(chatId(update), MESSAGE);
    }
}
