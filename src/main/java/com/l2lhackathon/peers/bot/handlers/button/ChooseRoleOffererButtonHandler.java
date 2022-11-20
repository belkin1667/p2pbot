package com.l2lhackathon.peers.bot.handlers.button;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChooseRoleOffererButtonHandler extends BaseButtonHandler {

    @Getter
    private final BotButton button = BotButton.I_AM_OFFERER;

    private final PeersBotResponseSender bot;

    @SneakyThrows
    @Override
    public void handle(Update update) {
        bot.sendMessage(chatId(update), "ChooseRoleOffererCommandHandler");
    }
}
