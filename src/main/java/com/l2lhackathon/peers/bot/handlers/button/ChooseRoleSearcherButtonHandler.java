package com.l2lhackathon.peers.bot.handlers.button;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChooseRoleSearcherButtonHandler extends BaseButtonHandler {

    @Getter
    private final BotButton button = BotButton.I_AM_SEARCHER;

    private final PeersBotResponseSender bot;

    @Override
    public void handle(Update update) {
        bot.sendMessage(chatId(update), "ChooseRoleSearcherCommandHandler");
    }
}
