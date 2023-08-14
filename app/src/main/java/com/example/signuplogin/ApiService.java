package com.example.signuplogin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<WeatherData> getWeather(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );
}
