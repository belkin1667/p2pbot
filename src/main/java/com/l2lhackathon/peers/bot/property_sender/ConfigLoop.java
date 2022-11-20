package com.l2lhackathon.peers.bot.property_sender;

import java.util.List;
import java.util.Optional;

import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.offer.OfferConfig;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfigLoop {

    private final List<PropertyRequestSender> senders;
    private final OfferFinalizationSender finalizationSender;
    private final UserRepository userRepository;

    public void doLoop(Offer offer, Update update, User user) {
        if (offer.getConfig().getProperties().size() <= user.getNextOfferConfigPropertyNumber()) {
            finalizationSender.send(update, user, offer);
            return;
        }

        var currentProperty = offer.getConfig().getProperties()
                .get(user.getNextOfferConfigPropertyNumber());
        var currentConstraint = currentProperty.getConstraint();
        PropertyRequestSender sender = senders.stream()
                .filter(p -> p.getType().equals(currentConstraint.getType()))
                .findAny()
                .orElseThrow(() -> new PeersHandlerNotFoundException(update));

        user.incrementNextOfferConfigPropertyNumber();
        user = userRepository.save(user);
        sender.send(update, user, currentProperty);
    }

    public Chat chat(Update update) {
        return message(update).chat();
    }

    public Message message(Update update) {
        return Optional.ofNullable(update.message()).orElseGet(() -> update.callbackQuery().message());
    }
}
