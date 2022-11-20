package com.l2lhackathon.peers.bot.handlers.button;


import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.controller.user.entity.DialogStage;
import com.l2lhackathon.peers.controller.user.entity.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Getter
public class EditCountryButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Ок, введи страну в ответ";

    private final BotButton button = BotButton.EDIT_COUNTRY;
    private final DialogStage dialogStageAfter = DialogStage.EDIT_COUNTRY;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;
    private SendResponse sendResponse;

    @Override
    public void handleAuthorized(Update update, User user) {
        sendResponse = bot.sendButtons(chat(update).id(), MESSAGE, List.of(BotButton.CLOSE_EDIT_PROFILE_DIALOG));
        user.setDialogStage(DialogStage.EDIT_COUNTRY);
        userRepository.save(user);
    }
}
