package com.l2lhackathon.peers.bot.handlers.command;


import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.l2lhackathon.peers.bot.handlers.button.BotButton.EDIT_PROFILE;

@Order(10000)
@Component
@RequiredArgsConstructor
@Getter
public class FreeTextHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Готово!";

    private final BotCommand command = BotCommand.FREE_TEXT;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;

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
        super.handle(update);
    }

    @Override
    public void handleAuthorized(Update update, User user) {
        var text = update.message().text();
        switch(user.getDialogStage()) {
            case EDIT_LAST_NAME -> user.setLastName(text);
            case EDIT_FIRST_NAME -> user.setFirstName(text);
            case EDIT_COUNTRY -> user.setCountry(text);
            case EDIT_CITY -> user.setCity(text);
            default -> { }
        }

        if (user.getDialogStage().getDeletePrevious() && user.getPreviousMessageId() != null) {
            bot.deleteMessage(chat(update).id(), user.getPreviousMessageId());
            user.setPreviousMessageId(null);
        }

        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);

        bot.sendButtons(chat(update).id(), MESSAGE, List.of(EDIT_PROFILE));
    }

}
