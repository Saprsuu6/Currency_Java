package com.example;

import com.example.config.BotConfig;
import com.example.config.BotInitializer;

public class App {
    public static void main(String[] args) {
        try {
            BotInitializer initializer = new BotInitializer(new TelegramBot(new BotConfig()));
            initializer.init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
