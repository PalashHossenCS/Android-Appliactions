package com.example.signuplogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimatio;
    ImageView logo;
    TextView title, slogan;
    public static int SPLASH_TIME_OUT=5500;

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


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences mSharedPref;
                mSharedPref=getSharedPreferences("SharedPref",MODE_PRIVATE);
                boolean isFirstTime=mSharedPref.getBoolean("firstTime",true);
                Toast.makeText(SplashActivity.this, isFirstTime+"", Toast.LENGTH_SHORT).show();

                if(!isFirstTime){
                    Intent intent=new Intent(SplashActivity.this,Homepage.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    SharedPreferences.Editor editor=mSharedPref.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    isFirstTime=mSharedPref.getBoolean("firstTime",true);
                    Toast.makeText(SplashActivity.this, isFirstTime+"", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        },SPLASH_TIME_OUT);




//
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    doWork();
//                    startApp();
//                }
//            });
//            thread.start();
        }
        //for timer

    public void doWork() {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public void startApp() {

    }
}