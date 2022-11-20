package com.l2lhackathon.peers.bot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "peers.bot.web-view.url")
public class BotWebViewProperties {

    private String protocol;
    private String host;
    private String users;

    public String getUserByIdPath(String id) {
        //return protocol + host + users + "/" + id;
        return "https://radiant-selkie-4b61f9.netlify.app/users";
    }
}
