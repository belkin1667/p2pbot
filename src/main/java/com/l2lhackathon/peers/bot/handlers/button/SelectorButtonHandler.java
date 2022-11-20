package com.l2lhackathon.peers.bot.handlers.button;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.bot.handlers.button.BaseButtonHandler;
import com.l2lhackathon.peers.bot.handlers.button.selector.SelectorHandler;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class SelectorButtonHandler extends BaseButtonHandler {

    private final BotButton button = BotButton.SELECTOR;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    private final List<SelectorHandler> handlers;

    @Override
    public void handleAuthorized(Update update, User user) throws PeersHandlerNotFoundException {
        var callback = update.callbackQuery().data();
        SelectorType type = SelectorType.from(callback)
                .orElseThrow(() -> new PeersEntityNotFoundException(SelectorType.class, callback));

        handlers.stream()
                .filter(h -> h.getType().equals(type))
                .findAny()
                .ifPresentOrElse(h -> h.handle(update, user), () -> { throw new PeersHandlerNotFoundException(update); });
    }

    @Override
    public void setupDialogStage(Update update, User user) {

    }
}
