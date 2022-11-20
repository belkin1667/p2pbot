package com.l2lhackathon.peers.bot.handlers.button;


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
public class DeleteProfileButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Профиль удален!";

    private final BotButton button = BotButton.DELETE_PROFILE;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handleAuthorized(Update update, User user) {
        userRepository.delete(user);
        bot.sendMessage(chat(update).id(), MESSAGE);
    }

    @Override
    public void postProcessAuthorized(Update update, User user) {

    }
}
