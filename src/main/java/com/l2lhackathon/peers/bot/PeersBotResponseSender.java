package com.l2lhackathon.peers.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.l2lhackathon.peers.bot.configuration.BotWebViewProperties;
import com.l2lhackathon.peers.bot.handlers.button.BotButton;
import com.l2lhackathon.peers.metrics.ActionLog;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.WebAppInfo;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PeersBotResponseSender {

    private final ActionLog actionLog;
    private final TelegramBot bot;
    private final BotWebViewProperties webViewProperties;

    @Value("${peers.bot.buttons.group-size}")
    private int groupSize;

    public SendResponse sendMessage(Long chatId, String message) {
        SendMessage sendMessageRequest = new SendMessage(chatId, message)
                .parseMode(ParseMode.Markdown);
        return bot.execute(sendMessageRequest);
    }


    public SendResponse sendButtons(Long chatId, String text, List<BotButton> buttons, Long webAppEntityId) {
        return sendButtons(chatId, text, buttons, String.valueOf(webAppEntityId));
    }

    public SendResponse sendButtons(Long chatId, String text, List<BotButton> buttons, String webAppEntityId) {
        AtomicInteger index = new AtomicInteger(0);
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();
        StreamEx.of(buttons)
                .map(btn -> toInlineKeyboardButton(btn, webAppEntityId))
                .forEach(btn -> {
                    List<InlineKeyboardButton> row;
                    int groupIndex = index.get() % groupSize;
                    if (groupIndex == 0) {
                        row = new ArrayList<>();
                        inlineKeyboardButtons.add(row);
                    } else {
                        row = inlineKeyboardButtons.get(inlineKeyboardButtons.size() - 1);
                    }
                    row.add(btn);
                    index.incrementAndGet();
                });

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                inlineKeyboardButtons.stream()
                        .map(list -> list.toArray(InlineKeyboardButton[]::new))
                        .toArray(InlineKeyboardButton[][]::new)
        );

        return bot.execute(new SendMessage(chatId, text).parseMode(ParseMode.Markdown).replyMarkup(inlineKeyboard));
    }

    public SendResponse sendButtons(Long chatId, String text, List<BotButton> buttons) {
        return sendButtons(chatId, text, buttons, "null");
    }

    private InlineKeyboardButton toInlineKeyboardButton(BotButton button, String id) {
        if (button.getWebViewEnabled()) {
            WebAppInfo url = new WebAppInfo(webViewProperties.getUserByIdPath(id));
            return new InlineKeyboardButton(button.getText())
                    .webApp(url);
        }
        return new InlineKeyboardButton(button.getText())
                .callbackData(button.getCallback());
    }

    public BaseResponse deleteMessage(Long chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        return bot.execute(deleteMessage);
    }
}
