package com.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.config.BotConfig;
import com.example.services.Constants;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    sendNewMessage(chatId, startCommandReceived(update.getMessage().getChat().getFirstName()));
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        } else if (update.hasCallbackQuery()) {
            String querry = update.getCallbackQuery().getData();

            retrofit.getCurrencyService().getCurrency().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JSONArray object = new JSONArray(response.body());
                        CurrencyModel currencyModel = null;

                        // if (querry == Constants.EURCallBack.getTitle()) {
                        // currencyModel = new CurrencyModel(object.getJSONObject(0));
                        // } else if (querry == Constants.USDCallBack.getTitle()) {
                        // currencyModel = new CurrencyModel(object.getJSONObject(1));
                        // }

                        // if (currencyModel != null)
                        // sendNewMessage(chatId, generateCurrencyAnswer(currencyModel));
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
    }

    private void sendNewMessage(Long chatId, String message) {
        sendMessage(chatId, message);
    }

    private String generateCurrencyAnswer(CurrencyModel model) {
        return String.format("ccy: %s\nbase ccy: %s\nbuy: %s\nsale:%s", model.getCcy(), model.getBase_ccy(),
                model.getBuy(), model.getSale());
    }

    private String startCommandReceived(String name) {
        return "Hi, " + name + ", nice to meet you!" + "\n" +
                "Chose the currency whose official exchange rate" + "\n" +
                "you want to know in relation to BYN.";
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        sendMessage.setReplyMarkup(getButtons());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private InlineKeyboardMarkup getButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButtonEUR = new InlineKeyboardButton();
        inlineKeyboardButtonEUR.setText(Constants.EURText.getTitle());
        inlineKeyboardButtonEUR.setCallbackData(Constants.EURCallBack.getTitle());

        InlineKeyboardButton inlineKeyboardButtonUSD = new InlineKeyboardButton();
        inlineKeyboardButtonUSD.setText(Constants.USDText.getTitle());
        inlineKeyboardButtonUSD.setCallbackData(Constants.USDCallBack.getTitle());

        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(inlineKeyboardButtonEUR);
        keyboardButtons.add(inlineKeyboardButtonUSD);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtons);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
