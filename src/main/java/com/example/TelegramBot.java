package com.example;

import org.json.JSONArray;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.config.BotConfig;
import com.example.services.ButtonsServise;
import com.example.services.Constants;
import com.example.services.MessageServise;

import lombok.AllArgsConstructor;
import retrofit2.Response;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final BaseRetrofit retrofit = BaseRetrofit.getInstance();

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) { // if typed message
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    try {
                        execute(new SendMessage(Long.toString(chatId), null,
                                MessageServise.start(update.getMessage().getChat().getFirstName()), null, null, null,
                                null,
                                ButtonsServise.currency(), null,
                                null, null));
                    } catch (TelegramApiException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        } else if (update.hasCallbackQuery()) { // if button pressed
            String querry = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (querry.equals(Constants.HomeText.getTitle())) { // if has been pressed button "home"
                try {
                    execute(new SendMessage(Long.toString(chatId), null,
                            MessageServise.start(update.getCallbackQuery().getMessage().getAuthorSignature()), null,
                            null, null,
                            null,
                            ButtonsServise.currency(), null,
                            null, null));

                } catch (TelegramApiException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (!querry.equals(Constants.HomeText.getTitle())) { // if has been pressed currency buttons
                try {
                    Response<String> response = retrofit.getCurrencyService().getCurrency().execute();
                    if (response.isSuccessful() && response.body() != null) {
                        JSONArray object = new JSONArray(response.body());
                        CurrencyModel currencyModel = null;

                        if (querry.equals(Constants.EURCallBack.getTitle())) {
                            currencyModel = new CurrencyModel(object.getJSONObject(0));
                        } else if (querry.equals(Constants.USDCallBack.getTitle())) {
                            currencyModel = new CurrencyModel(object.getJSONObject(1));
                        }

                        if (currencyModel != null) {
                            try {
                                execute(new SendMessage(Long.toString(chatId), null,
                                        MessageServise.currency(currencyModel), null, null, null, null,
                                        ButtonsServise.home(), null,
                                        null, null));
                            } catch (TelegramApiException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
