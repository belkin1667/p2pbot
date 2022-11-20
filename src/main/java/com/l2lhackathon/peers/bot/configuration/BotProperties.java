package com.l2lhackathon.peers.bot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "peers.bot")
public class BotProperties {

    @NotNull
    private String token;
    @NotNull
    private String login;
    @NotNull
    private String name;

    private int limit;

    private int offset;

    private int timeout;

}
