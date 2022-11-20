package com.l2lhackathon.peers.service.review;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewFilter {
    Long userTelegramId;
}
