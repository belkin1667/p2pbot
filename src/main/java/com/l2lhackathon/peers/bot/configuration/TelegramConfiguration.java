package com.l2lhackathon.peers.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramConfiguration {

    @Bean
    public TelegramBot telegramBot(BotProperties properties) {
        return new TelegramBot.Builder(properties.getToken())
                //.okHttpClient(new OkHttpClient())
                .build();
    }
}
