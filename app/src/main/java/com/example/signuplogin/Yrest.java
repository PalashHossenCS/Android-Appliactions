package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Yrest extends AppCompatActivity {

    private TextView temperatureTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yrest);

        temperatureTextView = findViewById(R.id.temperatureTextView);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<WeatherData> call = apiService.getWeather("Dhaka", "3d287bb938f7d23a9eafbadc7121e582");

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData.WeatherInfo weatherInfo = response.body().getWeatherInfo();
                    double temperature = weatherInfo.getTemperature();
                    temperatureTextView.setText("Temperature in Dhaka: " + temperature + "K");
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
