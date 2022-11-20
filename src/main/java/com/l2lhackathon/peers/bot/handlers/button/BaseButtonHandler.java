package com.l2lhackathon.peers.bot.handlers.button;

import java.util.Optional;

import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.l2lhackathon.peers.domain.user.User;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public abstract class BaseButtonHandler extends UpdateHandler {

    protected abstract BotButton getButton();

    @Override
    public void handle(Update update) {
        if (maybeButtonFromUpdate(update).orElseThrow().getWebViewEnabled()) {
            return;
        }
        super.handle(update);
    }

    @Override
    public void postProcessFinally(Update update) {
        getBot().deleteMessage(chat(update).id(), message(update).messageId());
    }

    @Override
    public boolean canHandle(Update update) {
        return maybeButtonFromUpdate(update)
                .filter(byCallBack -> byCallBack.equals(getButton()) || byCallBack.getWebViewEnabled())
                .isPresent();
    }

    private Optional<BotButton> maybeButtonFromUpdate(Update update) {
        return Optional.ofNullable(update)
                .map(Update::callbackQuery)
                .map(CallbackQuery::data)
                .flatMap(BotButton::findByCallbackData);
    }

    @Override
    public Chat chat(Update update) {
        return message(update).chat();
    }

    @Override
    public Message message(Update update) {
        return update.callbackQuery().message();
    }

    @Override
    public com.pengrad.telegrambot.model.User user(Update update) {
        return update.callbackQuery().from();
    }
}
