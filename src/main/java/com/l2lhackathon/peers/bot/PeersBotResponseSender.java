package com.l2lhackathon.peers.bot;

import java.util.List;

import javax.annotation.PostConstruct;

import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.l2lhackathon.peers.bot.handlers.button.Button;
import com.l2lhackathon.peers.metrics.Action;
import com.l2lhackathon.peers.metrics.ActionLog;
import com.l2lhackathon.peers.metrics.ActionType;
import com.l2lhackathon.peers.metrics.EntityType;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeersBotResponseSender {

    private final ActionLog actionLog;
    private final TelegramBot bot;

    public SendResponse sendMessage(Long chatId, String message) {
        SendMessage sendMessageRequest = new SendMessage(chatId, message)
                .parseMode(ParseMode.Markdown);
        return bot.execute(sendMessageRequest);
    }


    public SendResponse sendButtons(Long chatId, String text, List<Button> buttons) {
        var inlineButtons = buttons.stream().map(this::toInlineKeyboardButton).toArray(InlineKeyboardButton[]::new);
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(inlineButtons);
        return bot.execute(new SendMessage(chatId, text).parseMode(ParseMode.Markdown).replyMarkup(inlineKeyboard));
    }

    private InlineKeyboardButton toInlineKeyboardButton(Button button) {
        return new InlineKeyboardButton(button.getText()).callbackData(button.getCallback());
    }
}
