package com.l2lhackathon.peers.bot.controls;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectorOption {

    private String readableName;
    private String callbackData;

    public static SelectorOption of(String readableName, String callbackData) {
        return new SelectorOption(readableName, callbackData);
    }
}
