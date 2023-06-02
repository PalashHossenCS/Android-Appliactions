package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimatio;
    ImageView logo;
    TextView title, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_splash);

        //for animation
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_anomation);
        bottomAnimatio = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logo = findViewById(R.id.logoId);
        title = findViewById(R.id.titleId);
        slogan = findViewById(R.id.sloganId);

        logo.setAnimation(topAnimation);
        title.setAnimation(bottomAnimatio);
        slogan.setAnimation(bottomAnimatio);

        //for timer
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });

        thread.start();
    }
    public void doWork() {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void startApp() {
        Intent intent = new Intent(getApplicationContext(), BeforeLogin.class);
        startActivity(intent);
        finish();
    }
}