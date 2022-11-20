package com.l2lhackathon.peers.bot.property_sender;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.domain.offer.OfferProperty;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class IntegerPropertyRequestSender implements PropertyRequestSender {

    private final ConstraintType type = ConstraintType.SELECTOR_STRING;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void send(Update update, User user, OfferProperty property) {
        var response = bot.sendMessage(chat(update).id(), property.getName());
        user.setPreviousMessageId(response.message().messageId());
        user.setDialogStage(DialogStage.INTEGER_AWAITING);
        userRepository.save(user);
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
