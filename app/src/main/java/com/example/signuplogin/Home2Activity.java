package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.signuplogin.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home2Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    DetectFragment detectFragment = new DetectFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full Screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_home2);

        bottomNavigationView = findViewById(R.id.bottomNavbarId);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeId:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, homeFragment).commit();
                        return true;
                    case R.id.profileId:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, profileFragment).commit();
                        return true;
                    case R.id.detectId:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, detectFragment).commit();
                        return true;
                }
                return false;
            }
        });
        

    }
}