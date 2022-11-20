package com.l2lhackathon.peers.bot.handlers.button;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotButton;
import com.l2lhackathon.peers.bot.controls.Selector;
import com.l2lhackathon.peers.bot.controls.SelectorOption;
import com.l2lhackathon.peers.bot.controls.SelectorType;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.service.offer.OfferConfigFacade;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ChooseRoleOffererButtonHandler extends BaseButtonHandler {

    private static final String MESSAGE = "Выбери категорию предложения!";

    private final BotButton button = BotButton.I_AM_OFFERER;
    private final DialogStage dialogStageAfter = DialogStage.OFFERER_CHOSEN;
    private final PeersBotResponseSender bot;
    private final UserRepository userRepository;

    private final OfferConfigFacade offerConfigFacade;

    @Override
    public void handleAuthorized(Update update, User user) {
        var selectorOptions = offerConfigFacade.getAllActiveOfferNames().stream()
                .map(offer -> SelectorOption.of(offer.getName(), String.valueOf(offer.getId())))
                .toList();
        bot.sendSelector(chat(update).id(), MESSAGE, new Selector(selectorOptions, SelectorType.OFFER_TYPE));
    }
}
