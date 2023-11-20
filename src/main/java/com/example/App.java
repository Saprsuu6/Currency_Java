package com.example;

import com.example.services.Currency;

import lombok.extern.java.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class App {
    public static void main(String[] args) {
        BaseRetrofit retrofit = BaseRetrofit.getInstance();
        retrofit.getCurrencyService().getCurrency().enqueue(new Callback<String>() {
            @Override
            public final void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println(response.body());
                }
            }

            @Override
            public final void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
