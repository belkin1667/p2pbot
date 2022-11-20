package com.l2lhackathon.peers.bot.handlers.button;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ChooseRoleSearcherButtonHandler extends BaseButtonHandler {

    private final BotButton button = BotButton.I_AM_SEARCHER;
    private final DialogStage dialogStageAfter = DialogStage.SEARCHER_CHOSEN;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void handleAuthorized(Update update, User user) {
        bot.sendMessage(chat(update).id(), "ChooseRoleSearcherCommandHandler");
    }
}
