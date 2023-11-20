package com.example;

import org.json.JSONObject;

import lombok.Data;

@Data
public class CurrencyModel {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

    public CurrencyModel(JSONObject jsonObject) {
        ccy = jsonObject.getString("ccy");
        base_ccy = jsonObject.getString("base_ccy");
        buy = jsonObject.getString("buy");
        sale = jsonObject.getString("sale");
    }
}
