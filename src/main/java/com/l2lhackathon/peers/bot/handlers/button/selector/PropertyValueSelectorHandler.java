package com.l2lhackathon.peers.bot.handlers.button.selector;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.Selector;
import com.l2lhackathon.peers.bot.controls.SelectorOption;
import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.bot.property_sender.ConfigLoop;
import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.offer.OfferConfig;
import com.l2lhackathon.peers.domain.offer.OfferElement;
import com.l2lhackathon.peers.domain.offer.OfferProperty;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
import com.l2lhackathon.peers.domain.offer.constraints.StringSelectorConstraint;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.offer.OfferConfigFacade;
import com.l2lhackathon.peers.service.offer.OfferRepository;
import com.l2lhackathon.peers.service.offer.PropertyRepository;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class PropertyValueSelectorHandler extends SelectorHandler {

    private static final String ERROR_MESSAGE = "Не удалось создать заявку! Попробуйте снова /start";

    private final SelectorType type = SelectorType.PROPERTY_VALUE;
    private final OfferConfigFacade offerConfigFacade;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;
    private final ConfigLoop configLoop;
    private final PropertyRepository propertyRepository;
    private final OfferRepository offerRepository;

    @Override
    public void handle(Update update, User user) {
        if (user.getDialogStage() == DialogStage.STRING_SELECTOR_AWAITING) {
            handleStringSelectorAwaiting(update, user);
        } else {
            handleDefault(update, user);
        }
    }

    private void handleDefault(Update update, User user) {
        user.setCurrentOfferId(null);
        user.setNextOfferConfigPropertyNumber(null);
        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);
        bot.sendMessage(chat(update).id(), ERROR_MESSAGE);
    }


    private void handleStringSelectorAwaiting(Update update, User user) {
        var callback = type.getEffectiveCallbackFrom(update.callbackQuery().data()); //"offerId:propertyId:value"
        var offerId = Long.valueOf(callback.split(":")[0]);
        var propertyId = Long.valueOf(callback.split(":")[1]);
        var value = callback.split(":")[2];
        var property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new PeersEntityNotFoundException(OfferProperty.class, propertyId));

        if (!((StringSelectorConstraint) property.getConstraint()).getValues().contains(value)) {
            throw new IllegalStateException();
        }

        var offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new PeersEntityNotFoundException(Offer.class, offerId));
        OfferElement offerElement = new OfferElement();
        offerElement.init(value, offer, property);
        offer.getOfferElements().add(offerElement);
        offer = offerRepository.save(offer);

        configLoop.doLoop(offer, update, user);
    }
}
