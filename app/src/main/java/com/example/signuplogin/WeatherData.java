package com.example.signuplogin;

import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("main")
    private WeatherInfo weatherInfo;

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public class WeatherInfo {

        @SerializedName("temp")
        private double temperature;

        public double getTemperature() {
            return temperature;
        }
    }
}
