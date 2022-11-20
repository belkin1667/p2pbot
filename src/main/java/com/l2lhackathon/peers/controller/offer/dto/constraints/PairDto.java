package com.l2lhackathon.peers.controller.offer.dto.constraints;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PairDto<A, B> {
    A key;
    B value;
}
