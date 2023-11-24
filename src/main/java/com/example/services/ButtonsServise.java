package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class ButtonsServise {
    public static InlineKeyboardMarkup home() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButtonHome = new InlineKeyboardButton();
        inlineKeyboardButtonHome.setText(Constants.HomeText.getTitle());
        inlineKeyboardButtonHome.setCallbackData(Constants.HomeText.getTitle());

        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(inlineKeyboardButtonHome);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtons);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup currency() {
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
