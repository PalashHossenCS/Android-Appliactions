package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {

    TextView detection, seepost, map, contactus, videos,help;
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
        help =findViewById(R.id.help);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
                Intent intent = new Intent(Homepage.this, Social.class);
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

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, RestApi.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences mSharedPref;
//                mSharedPref=getSharedPreferences("SharedPref",MODE_PRIVATE);
//                SharedPreferences.Editor editor=mSharedPref.edit();
//                editor.putBoolean("firstTime",true);
//                editor.commit();

                SharedPreferences mSharedPref;
                mSharedPref=getSharedPreferences("SharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor=mSharedPref.edit();
                editor.putString("userName", "null");
                editor.commit();

                mAuth.signOut();
                signOutUser();
            }
        });



    }

    private void signOutUser() {
        Intent intent=new Intent(Homepage.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}