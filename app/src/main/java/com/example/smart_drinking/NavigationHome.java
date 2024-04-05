package com.example.smart_drinking;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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

    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);
        networkChangeReceiver = new NetworkChangeReceiver();

        LinearLayout layout_gotita = findViewById(R.id.layout_gotita);

        layout_gotita.setVisibility(View.GONE);


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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }
}