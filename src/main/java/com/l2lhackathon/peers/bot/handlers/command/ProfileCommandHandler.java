package com.l2lhackathon.peers.bot.handlers.command;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.handlers.button.BotButton;
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
public class ProfileCommandHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Ваш профиль: %s";

    @Getter
    private final BotCommand command = BotCommand.PROFILE;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handle(Update update) {
        var telegramUser = user(update);
        var maybeUser = userRepository.findByTelegramId(telegramUser.id());
        User user;
        if (maybeUser.isEmpty()) {
            user = new User();
            user.init(telegramUser.firstName(), telegramUser.lastName(), telegramUser.username(), telegramUser.id());
            userRepository.save(user);
        } else {
            user = maybeUser.get();
        }

        bot.sendButtons(
                chatId(update),
                MESSAGE.formatted(user.toBotMessage()),
                List.of(BotButton.EDIT_PROFILE)
        );
    }
}
