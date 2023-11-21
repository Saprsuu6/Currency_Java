package com.example.services;

public enum Constants {
    EURText("EUR"),
    USDText("USD"),
    EURCallBack("EUR has been pressed"),
    USDCallBack("USD has been pressed");

    private String title;

    Constants(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
