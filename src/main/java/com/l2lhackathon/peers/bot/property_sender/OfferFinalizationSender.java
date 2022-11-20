package com.l2lhackathon.peers.bot.property_sender;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferFinalizationSender {

    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    public void send(Update update, User user, Offer offer) {
        bot.sendButtons(chat(update).id(), "Ваше предложение '%s' создано и опубликовано!".formatted(offer.getConfig().getName()), List.of(BotButton.SHOW_MY_OFFERS));

        user.setCurrentOfferId(null);
        user.setNextOfferConfigPropertyNumber(null);
        user.setPreviousMessageId(null);
        user = userRepository.save(user);
    }

    public Chat chat(Update update) {
        return message(update).chat();
    }

    public Message message(Update update) {
        return update.callbackQuery().message();
    }

    public com.pengrad.telegrambot.model.User user(Update update) {
        return update.callbackQuery().from();
    }
}
