package com.l2lhackathon.peers.controller.offer.dto;

import lombok.Data;

@Data
public class Update<T> {

    private T value;

    public T get() {
        return value;
    }
}
