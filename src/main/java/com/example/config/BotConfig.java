package com.example.config;

import lombok.Data;

@Data
public class BotConfig {
    public String botName;
    public String token;

    private BotConfig(String botName, String token) {
        this.botName = botName;
        this.token = token;
    }
}
