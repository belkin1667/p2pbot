package com.l2lhackathon.peers.bot.handlers.command;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.handlers.button.BotButton;
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
public class StartCommandHandler extends BaseCommandHandler {

    private static final String BASE_FLOW_MESSAGE = "Выбери, что ты хочешь сделать:";

    @Getter
    private final BotCommand command = BotCommand.START;

    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;
    private final ProfileCommandHandler profileCommandHandler;

    @Override
    public void handle(Update update) {
        var telegramId = update.message().from().id();
        var user = userRepository.findByTelegramId(telegramId);
        if (user.isPresent()) {
            baseFlow(update, user.get());
        } else {
            requestRegistration(update);
        }
    }

    private void baseFlow(Update update, User user) {
        bot.sendButtons(
                chatId(update),
                BASE_FLOW_MESSAGE,
                List.of(BotButton.I_AM_SEARCHER, BotButton.I_AM_OFFERER)
        );

        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);
    }

    private void requestRegistration(Update update) {
        profileCommandHandler.handle(update);
    }
}
