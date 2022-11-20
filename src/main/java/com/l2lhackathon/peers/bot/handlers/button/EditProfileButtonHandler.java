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
public class EditProfileButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Ок, выбери что хочешь сменить";

    @Getter
    private final BotButton button = BotButton.EDIT_PROFILE;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendButtons(
            chat(update).id(),
            MESSAGE,
            List.of(
                BotButton.EDIT_FIRST_NAME,
                BotButton.EDIT_LAST_NAME,
                BotButton.EDIT_CITY,
                BotButton.EDIT_COUNTRY,
                BotButton.DELETE_PROFILE,
                BotButton.CLOSE_EDIT_PROFILE_DIALOG
            )
        );
    }
}
