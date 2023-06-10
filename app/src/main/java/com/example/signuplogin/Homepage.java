package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    TextView detection, seepost, map, contactus, videos;
    Button logout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        detection = findViewById(R.id.Detect);
        seepost = findViewById(R.id.Seepost);
        map = findViewById(R.id.SeeMap);
        videos = findViewById(R.id.WatchVid);
        contactus = findViewById(R.id.help);
        logout = findViewById(R.id.logout);

        detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Detection.class);
                startActivity(intent);
            }
        });
        seepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, PostActivity.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, MultipleDropDown.class);
                startActivity(intent);
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Home2Activity.class);
                startActivity(intent);
            }
        });




    }


}