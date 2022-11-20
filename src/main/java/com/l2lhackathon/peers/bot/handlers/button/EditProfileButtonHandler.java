package com.l2lhackathon.peers.bot.handlers.button;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditProfileButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Ок, выбери что хочешь сменить";

    @Getter
    private final BotButton button = BotButton.EDIT_PROFILE;

    private final PeersBotResponseSender bot;

    @Override
    public void handle(Update update) {
        bot.sendButtons(
            chatId(update),
            MESSAGE,
            List.of(
                BotButton.EDIT_FIRST_NAME,
                BotButton.EDIT_LAST_NAME,
                BotButton.EDIT_CITY,
                BotButton.EDIT_COUNTRY,
                BotButton.CLOSE_EDIT_PROFILE_DIALOG
            )
        );
    }
}
