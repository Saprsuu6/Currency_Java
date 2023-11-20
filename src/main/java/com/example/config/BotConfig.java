package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    public String botName = "OfficialRateOfBYNBot";
    @Value("${bot.token}")
    public String token = "6600433696:AAGJ3ReHFYoVwrVNoLnKWeH_WssigGQah74";
}
