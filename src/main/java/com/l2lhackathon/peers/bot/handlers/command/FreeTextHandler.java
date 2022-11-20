package com.l2lhackathon.peers.bot.handlers.command;


import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.controller.user.entity.DialogStage;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.l2lhackathon.peers.bot.handlers.button.BotButton.EDIT_PROFILE;

@Order(10000)
@Component
@RequiredArgsConstructor
@Transactional
public class FreeTextHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Готово!";

    @Getter
    private final BotCommand command = BotCommand.FREE_TEXT;

    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;
    private final ProfileCommandHandler profileCommandHandler;

    @Override
    public boolean canHandle(Update update) {
        return false;
    }

    @Override
    public void handle(Update update) {
        if (update == null) {
            throw new IllegalStateException("Update is null");
        }
        if (update.message() == null || update.message().text() == null) {
            throw new PeersHandlerNotFoundException(update);
        }

        var user = userRepository.findByTelegramId(user(update).id());
        if (user.isPresent()) {
            baseFlow(update, user.get());
        } else {
            requestRegistration(update);
        }
    }

    private void baseFlow(Update update, User user) {
        var text = update.message().text();
        switch(user.getDialogStage()) {
            case EDIT_LAST_NAME -> user.setLastName(text);
            case EDIT_FIRST_NAME -> user.setFirstName(text);
            case EDIT_COUNTRY -> user.setCountry(text);
            case EDIT_CITY -> user.setCity(text);
            default -> { }
        }
        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);

        bot.sendButtons(chatId(update), MESSAGE, List.of(EDIT_PROFILE));
    }

    private void requestRegistration(Update update) {
        profileCommandHandler.handle(update);
    }
}
