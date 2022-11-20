package com.l2lhackathon.peers.domain.offer.constraints;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair<A, B> {
    A key;
    B value;
}
