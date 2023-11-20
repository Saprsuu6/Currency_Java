package com.example;

import com.example.services.Currency;

import lombok.extern.java.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // try {
        // BaseRetrofit retrofit = BaseRetrofit.getInstance();
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Currency service = retrofit.create(Currency.class);
        service.getCurrency().enqueue(new Callback<String>() {
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
