package com.example.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Currency {
    @GET("p24api/pubinfo?exchange&coursid=5")
    Call<String> getCurrency();
}
