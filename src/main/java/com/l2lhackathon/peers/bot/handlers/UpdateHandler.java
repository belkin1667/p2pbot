package com.l2lhackathon.peers.bot.handlers;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.lang.Nullable;

public abstract class UpdateHandler {

    private static final String UNKNOWN_USER_MESSAGE = "Я тебя не знаю! Используй команду /profile, чтобы создать " +
            "профиль!";

    public abstract UserRepository getUserRepository();

    public abstract PeersBotResponseSender getBot();

    public abstract DialogStage getDialogStageAfter();

    @Nullable
    public SendResponse getSendResponse() {
        return null;
    }

    public void handle(Update update) {
        var maybeUser = getUserRepository().findByTelegramId(user(update).id());
        if (maybeUser.isEmpty()) {
            handleUnauthorized(update);
        } else {
            handleAuthorized(update, maybeUser.get());
            postProcessAuthorized(update, maybeUser.get());
        }
        postProcessFinally(update);
    }

    public abstract void handleAuthorized(Update update, User user);

    public void postProcessAuthorized(Update update, User user) {
        user.setDialogStage(getDialogStageAfter());
        if (getSendResponse() != null) {
            persistLastMessage(getSendResponse().message(), user);
        }
        getUserRepository().save(user);
    }

    public void handleUnauthorized(Update update) {
        getBot().sendMessage(chat(update).id(), UNKNOWN_USER_MESSAGE);
    }

    public void postProcessFinally(Update update) {

    }

    public abstract boolean canHandle(Update update);

    public abstract Message message(Update update);

    public abstract Chat chat(Update update);

    public abstract com.pengrad.telegrambot.model.User user(Update update);

    public void persistLastMessage(Message message, User user) {
        user.setPreviousMessageId(message.messageId());
        getUserRepository().save(user);
    }

}
