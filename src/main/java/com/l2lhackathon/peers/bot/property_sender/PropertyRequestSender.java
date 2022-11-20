package com.l2lhackathon.peers.bot.property_sender;

import com.l2lhackathon.peers.domain.offer.OfferProperty;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
import com.l2lhackathon.peers.domain.user.User;
import com.pengrad.telegrambot.model.Update;

public interface PropertyRequestSender {
    void send(Update update, User user, OfferProperty property);
    ConstraintType getType();
}
