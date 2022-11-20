package com.l2lhackathon.peers.domain.user;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Delegate;

@Value
@Builder
public class UserWith<T> {
    @Delegate User user;
    T data;
}
