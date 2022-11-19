package com.l2lhackathon.peers.bot.exception;

import com.l2lhackathon.peers.exception.PeersCoreException;
import com.pengrad.telegrambot.model.Update;

public class PeersHandlerNotFoundException extends PeersCoreException {

    private static final int NOT_FOUND = 404;

    public PeersHandlerNotFoundException(Update update) {
        super("Update %s can not be handled! Handler not found!".formatted(update.toString()), NOT_FOUND);
    }
}
