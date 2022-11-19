package com.l2lhackathon.peers.metrics;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Action {
    private final Instant createdAt = Instant.now();
    private ActionType actionType;
    private EntityType entityType;
    private String entityId;
    private String context;
}
