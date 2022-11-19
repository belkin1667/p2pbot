package com.l2lhackathon.peers.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PeersCoreException extends RuntimeException {
    private String message;
    private int code;
}
