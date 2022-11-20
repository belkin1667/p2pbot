package com.l2lhackathon.peers.bot.handlers.command;


import java.util.List;

import com.l2lhackathon.peers.bot.PeersBotResponseSender;
import com.l2lhackathon.peers.bot.controls.BotCommand;
import com.l2lhackathon.peers.bot.exception.PeersHandlerNotFoundException;
import com.l2lhackathon.peers.bot.property_sender.ConfigLoop;
import com.l2lhackathon.peers.domain.offer.Offer;
import com.l2lhackathon.peers.domain.offer.OfferElement;
import com.l2lhackathon.peers.domain.offer.OfferProperty;
import com.l2lhackathon.peers.domain.offer.OfferPropertyType;
import com.l2lhackathon.peers.domain.offer.constraints.IntegerGreaterOrLessConstraint;
import com.l2lhackathon.peers.domain.user.DialogStage;
import com.l2lhackathon.peers.domain.user.User;
import com.l2lhackathon.peers.exception.PeersEntityNotFoundException;
import com.l2lhackathon.peers.service.offer.OfferRepository;
import com.l2lhackathon.peers.service.user.UserRepository;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.l2lhackathon.peers.bot.controls.BotButton.EDIT_PROFILE;

@Order(10000)
@Component
@RequiredArgsConstructor
@Getter
public class FreeTextHandler extends BaseCommandHandler {

    private static final String MESSAGE = "Готово!";

    private final BotCommand command = BotCommand.FREE_TEXT;
    private final DialogStage dialogStageAfter = DialogStage.UNKNOWN;
    private final UserRepository userRepository;
    private final PeersBotResponseSender bot;

    private final OfferRepository offerRepository;
    private final ConfigLoop configLoop;


    @Override
    public void setupDialogStage(Update update, User user) {

    }

    @Override
    public boolean canHandle(Update update) {
        return false;
    }

    @Override
    public void handle(Update update) {
        if (update == null) {
            throw new IllegalStateException("Update is null");
        }
        if (message(update) == null || message(update).text() == null) {
            throw new PeersHandlerNotFoundException(update);
        }
        super.handle(update);
    }

    @Override
    public void handleAuthorized(Update update, User user) {
        var text = update.message().text();
        switch (user.getDialogStage()) {
            case EDIT_LAST_NAME -> {
                user.setLastName(text);
                finalizeEditParam(update, user);
            }
            case EDIT_FIRST_NAME -> {
                user.setFirstName(text);
                finalizeEditParam(update, user);
            }
            case EDIT_COUNTRY -> {
                user.setCountry(text);
                finalizeEditParam(update, user);
            }
            case EDIT_CITY -> {
                user.setCity(text);
                finalizeEditParam(update, user);
            }
            case INTEGER_AWAITING -> {
                handleIntegerAwaiting(update, user, text);
            }
            default -> { }
        }
    }

    private void finalizeAwaiting(Update update, User user) {
        if (user.getDialogStage().getDeletePrevious() && user.getPreviousMessageId() != null) {
            bot.deleteMessage(chat(update).id(), user.getPreviousMessageId());
            user.setPreviousMessageId(null);
        }
    }

    private void finalizeEditParam(Update update, User user) {
        if (user.getDialogStage().getDeletePrevious() && user.getPreviousMessageId() != null) {
            bot.deleteMessage(chat(update).id(), user.getPreviousMessageId());
            user.setPreviousMessageId(null);
        }

        user.setDialogStage(DialogStage.UNKNOWN);
        userRepository.save(user);

        bot.sendButtons(chat(update).id(), MESSAGE, List.of(EDIT_PROFILE));
        super.setupDialogStage(update, user);
    }

    private void handleIntegerAwaiting(Update update, User user, String text) {
        Integer value;
        try {
            value = Integer.valueOf(text);
        } catch (NumberFormatException nfe) {
            bot.sendMessage(chat(update).id(), "Введи число!");
            return;
        }

        final Offer[] offer = {offerRepository.findById(user.getCurrentOfferId())
                .orElseThrow(() -> new PeersEntityNotFoundException(Offer.class, user.getCurrentOfferId()))};
        var property = offer[0].getConfig().getProperties().get(user.getNextOfferConfigPropertyNumber() - 1);
        if (property.getType() != OfferPropertyType.INTEGER) {
            throw new IllegalStateException();
        }
        IntegerGreaterOrLessConstraint constraint = (IntegerGreaterOrLessConstraint) property.getConstraint();
        constraint.getValues().stream()
                .filter(v -> !v.getKey().getComparator().apply(value, v.getValue()))
                .findAny().ifPresentOrElse(v -> {
                            bot.sendMessage(
                                    chat(update).id(),
                                    "Число должно быть %s %d".formatted(v.getKey().getReadableName(), v.getValue())
                            );
                            user.decrementNextOfferConfigPropertyNumber();
                            //finalizeAwaiting(update, user);
                            configLoop.doLoop(offer[0], update, user);
                        },
                        () -> {
                            OfferElement offerElement = new OfferElement();
                            offerElement.init(String.valueOf(value), offer[0], property);
                            offer[0].getOfferElements().add(offerElement);
                            offer[0] = offerRepository.save(offer[0]);
                            //user.setDialogStage(DialogStage.UNKNOWN);
                            //finalizeAwaiting(update, user);
                            configLoop.doLoop(offer[0], update, user);
                        });
    }

}
