package com.l2lhackathon.peers.bot;

import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.l2lhackathon.peers.bot.configuration.BotProperties;
import com.l2lhackathon.peers.bot.handlers.UpdateHandler;
import com.l2lhackathon.peers.bot.handlers.command.FreeTextHandler;
import com.l2lhackathon.peers.metrics.Action;
import com.l2lhackathon.peers.metrics.ActionLog;
import com.l2lhackathon.peers.metrics.ActionType;
import com.l2lhackathon.peers.metrics.EntityType;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PeersBotUpdateReceiver {

    private final BotProperties properties;
    private final ActionLog actionLog;
    private final List<UpdateHandler> handlers;
    private final FreeTextHandler freeTextHandler;

    private final TelegramBot bot;
    private GetUpdates getUpdates;

    @PostConstruct
    void setUp() {
        getUpdates = new GetUpdates()
                            .limit(properties.getLimit())
                            .offset(properties.getOffset())
                            .timeout(properties.getTimeout());
    }

    @Scheduled(fixedDelay = 20) // processed previous, started new one
    public void getAndProcessUpdates() {
        List<Update> updatesBatch = getNextUpdatesBatch();
        processUpdateBatch(updatesBatch);
    }

    private List<Update> getNextUpdatesBatch() {
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        return updatesResponse.updates();
    }

    private void processUpdateBatch(List<Update> updates) {
        getUpdates.offset(updates.stream().map(Update::updateId).max(Comparator.naturalOrder()).orElse(0) + 1);
        updates.forEach(this::processUpdate);
    }

    private void processUpdate(Update update) {
        actionLog.logAction(Action.builder()
                .entityType(EntityType.TELEGRAM_UPDATE)
                .entityId(String.valueOf(update.updateId()))
                .actionType(ActionType.TELEGRAM_UPDATE_RECEIVED)
                .build()
        );

        handlers.stream()
                .filter(h -> h.canHandle(update)).findAny()
                .ifPresentOrElse(h -> h.handle(update), () -> freeTextHandler.handle(update));
    }
}
