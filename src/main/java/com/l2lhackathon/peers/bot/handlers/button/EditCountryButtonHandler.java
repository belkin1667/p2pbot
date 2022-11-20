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
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class EditCountryButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Ок, введи страну в ответ";
    private static final String UNKNOWN_USER_MESSAGE = "Я тебя не знаю! Используй команду /profile, чтобы создать профиль!";

    @Getter
    private final BotButton button = BotButton.EDIT_COUNTRY;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handle(Update update) {
        var maybeUser = userRepository.findByTelegramId(user(update).id());
        if (maybeUser.isEmpty()) {
            bot.sendMessage(chatId(update), UNKNOWN_USER_MESSAGE);
            return;
        }
        bot.sendButtons(chatId(update), MESSAGE, List.of(BotButton.CLOSE_EDIT_PROFILE_DIALOG));

        User user = maybeUser.get();
        user.setDialogStage(DialogStage.EDIT_COUNTRY);
        userRepository.save(user);
    }
}
