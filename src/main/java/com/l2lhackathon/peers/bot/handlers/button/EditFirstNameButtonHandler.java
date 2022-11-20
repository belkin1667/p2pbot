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
public class EditFirstNameButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Ок, введи свое имя в ответ";
    private static final String UNKNOWN_USER_MESSAGE = "Я тебя не знаю! Используй команду /profile, чтобы создать профиль!";

    @Getter
    private final BotButton button = BotButton.EDIT_FIRST_NAME;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handle(Update update) {
        var telegramId = update.callbackQuery().message().from().id();
        var maybeUser = userRepository.findByTelegramId(telegramId);
        if (maybeUser.isEmpty()) {
            bot.sendMessage(chatId(update), UNKNOWN_USER_MESSAGE);
            return;
        }
        bot.sendButtons(chatId(update), MESSAGE, List.of(BotButton.CLOSE_EDIT_PROFILE_DIALOG));

        User user = maybeUser.get();
        user.setDialogStage(DialogStage.EDIT_FIRST_NAME);
        userRepository.save(user);
    }
}
