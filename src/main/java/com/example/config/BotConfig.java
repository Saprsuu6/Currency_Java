package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import lombok.Data;

@Configuration
@Data
@PropertySource("../../../../../../application.properties")
public class BotConfig {
    @Value("${bot.name}")
    public String botName;
    @Value("${bot.token}")
    public String token;
}
