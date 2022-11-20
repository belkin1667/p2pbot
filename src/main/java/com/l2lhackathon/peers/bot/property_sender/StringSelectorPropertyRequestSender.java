package com.l2lhackathon.peers.bot.property_sender;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.Selector;
import com.l2lhackathon.peers.bot.controls.SelectorOption;
import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.domain.offer.OfferProperty;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
import com.l2lhackathon.peers.domain.offer.constraints.StringSelectorConstraint;
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
public class StringSelectorPropertyRequestSender implements PropertyRequestSender {

    private final ConstraintType type = ConstraintType.SELECTOR_STRING;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    @Override
    public void send(Update update, User user, OfferProperty property) {
        bot.sendSelector(
                chat(update).id(),
                property.getName(),
                new Selector(
                        ((StringSelectorConstraint) property.getConstraint())
                                .getValues()
                                .stream()
                                .map(s -> new SelectorOption(s, property.getId() + ":" + s))
                                .toList(),
                        SelectorType.PROPERTY_VALUE
                )
        );

        user.setDialogStage(DialogStage.STRING_SELECTOR_AWAITING);
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
