package com.l2lhackathon.peers.domain.offer.constraints;

import lombok.Data;

@Data
public class Pair<A, B> {
    A key;
    B value;
}
