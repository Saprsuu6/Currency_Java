package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.BotConfig;
import com.example.config.BotInitializer;

public class App {
    public static void main(String[] args) {
        // try(AnnotationConfigApplicationContext appContext = new
        // AnnotationConfigApplicationContext(BotConfig.class)) {

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(BotConfig.class);
        BotConfig botConfig = appContext.getBean("botConfigBean", BotConfig.class);

        try {
            BotInitializer initializer = new BotInitializer(new TelegramBot(botConfig));
            initializer.init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
