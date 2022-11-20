package com.l2lhackathon.peers.bot.controls;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Selector {
    private List<SelectorOption> options;
    private SelectorType type;
}
