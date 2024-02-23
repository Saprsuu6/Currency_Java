package com.example;

import org.json.JSONObject;

public class App {
    public static void main(String[] args) {
        JSONObject config = new JSONObject("../../../resources/resources.json");
        config.getString("asd");
        // try {
        // BotInitializer initializer = new BotInitializer(new TelegramBot(new
        // BotConfig()));
        // initializer.init();
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
    }
}
