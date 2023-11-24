package com.example.services;

import com.example.CurrencyModel;

public class MessageServise {
    public static String currency(CurrencyModel model) {
        return String.format("ccy: %s\nbase ccy: %s\nbuy: %s\nsale:%s", model.getCcy(), model.getBase_ccy(),
                model.getBuy(), model.getSale());
    }

    public static String start(String name) {
        return "Hi, " + name + ", nice to meet you!" + "\n" +
                "Chose the currency whose official exchange rate" + "\n" +
                "you want to know in relation to BYN.";
    }
}
