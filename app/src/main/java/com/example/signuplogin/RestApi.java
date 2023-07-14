package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class RestApi extends AppCompatActivity {

    private TextView textView;
    Button rating,sass,gsapp,graphql;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        textView = findViewById(R.id.textView);

        rating=findViewById(R.id.rating);
        sass=findViewById(R.id.sass);
        gsapp=findViewById(R.id.gsapp);
        graphql=findViewById(R.id.gql);

        graphql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestApi.this, RestApi.class);
                startActivity(intent);
            }
        });

        gsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestApi.this, Gsapp.class);
                startActivity(intent);
            }
        });

        sass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestApi.this, SassAcivity.class);
                startActivity(intent);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestApi.this, RatingApp.class);
                startActivity(intent);
            }
        });

        makeApiCall();
    }

    private void makeApiCall() {
        OkHttpClient client = new OkHttpClient();

        String url = "https://www.overleaf.com/login";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("API call Successful");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("API call failed");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("API call failed due to network error");
                    }
                });
            }
        });
    }
}
