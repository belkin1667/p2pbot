package com.l2lhackathon.peers.bot.handlers.button.selector;

import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.bot.property_sender.ConfigLoop;
import com.l2lhackathon.peers.bot.property_sender.PropertyRequestSender;
import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.offer.OfferConfigFacade;
import com.l2lhackathon.peers.service.offer.OfferRepository;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class OfferTypeSelectorHandler extends SelectorHandler {

    private static final String ERROR_MESSAGE = "Не удалось создать заявку! Попробуйте снова /start";

    private final SelectorType type = SelectorType.OFFER_TYPE;
    private final OfferConfigFacade offerConfigFacade;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;
    private final ConfigLoop configLoop;

    @Override
    public void handle(Update update, User user) {
        if (user.getDialogStage() == DialogStage.OFFERER_CHOSEN) {
            handleOfferedChosen(update, user);
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

    private void handleOfferedChosen(Update update, User user) {
        var callback = type.getEffectiveCallbackFrom(update.callbackQuery().data());
        var selectedOfferConfig = offerConfigFacade.findById(Long.valueOf(callback));

        Offer offer = new Offer();
        offer.init(user, selectedOfferConfig);
        offer = offerRepository.save(offer);

        user.setCurrentOfferId(offer.getId());
        user.setNextOfferConfigPropertyNumber(0);
        user.setDialogStage(DialogStage.OFFER_CONFIG_CHOSEN);
        user = userRepository.save(user);

        configLoop.doLoop(offer, update, user);

    }
}
