package com.example;

import java.lang.invoke.ClassSpecializer.Factory;

import com.example.services.Currency;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BaseRetrofit {
    private final static String baseUrl = "https://api.privatbank.ua";
    private static BaseRetrofit instance = null;
    private Currency currencyServise;

    public Currency getCurrencyService() {
        return currencyServise;
    }

    private BaseRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Currency service = retrofit.create(Currency.class);
    }

    public static BaseRetrofit getInstance() {
        if (instance == null)
            instance = new BaseRetrofit();
        return instance;
    }
}
