package com.l2lhackathon.peers.bot.handlers.command;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.PeersBotUpdateReceiver;
import com.l2lhackathon.peers.bot.handlers.button.Button;
import com.l2lhackathon.peers.domain.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommandHandler extends BaseCommandHandler {

    private static final String REGISTRATION_REQUEST_MESSAGE = "Привет! Тебе нужно зарегистрироваться";
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
        bot.sendButtons(update.message().chat().id(), BASE_FLOW_MESSAGE, List.of(Button.I_AM_SEARCHER, Button.I_AM_OFFERER));
    }

    private void requestRegistration(Update update) {
        bot.sendMessage(update.message().chat().id(), REGISTRATION_REQUEST_MESSAGE);
        profileCommandHandler.handle(update);
    }
}
