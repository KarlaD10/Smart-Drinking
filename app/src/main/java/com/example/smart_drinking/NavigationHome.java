package com.example.smart_drinking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.smart_drinking.ui.Historic.HistoricFragment;
import com.example.smart_drinking.ui.Home.HomeFragment;
import com.example.smart_drinking.ui.Settings.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationHome extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    SettingFragment settingFragment = new SettingFragment();
    HistoricFragment historicFragment = new HistoricFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id  = item.getItemId();
            if (id == R.id.home){
                getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                return  true;
            } else if (id == R.id.notification) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,historicFragment).commit();
                return  true;
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();
                return  true;
            }
        });

    }
}