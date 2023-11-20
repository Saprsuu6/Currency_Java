package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.TelegramBot;

public class BotInitializer {
    private final TelegramBot telegramBot;

    @Autowired
    public BotInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
