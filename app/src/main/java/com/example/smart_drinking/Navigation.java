package com.example.smart_drinking;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

public class Navigation extends AppCompatActivity {

    ViewPager slideViewPager;
    LinearLayout dotIndicator;
    ViewPagerAdapter viewPagerAdapter;
    Button backButton, skipButton, nextButton;
    TextView[]dots;

    ViewPager.OnPageChangeListener viewPageListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setDotIndicato(position);
            if (position>0){
                backButton.setVisibility(View.VISIBLE);
            }else{
                backButton.setVisibility(View.INVISIBLE);
            }

            if (position==2)
            {
                nextButton.setText("Finalizar");
            }else{
                nextButton.setText("Siguiente");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);

        backButton = findViewById(R.id.btn_back);
        skipButton = findViewById(R.id.skipButton);
        nextButton = findViewById(R.id.btn_next);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0)>0){
                    slideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0)<2){
                    slideViewPager.setCurrentItem(getItem(1), true);
                }else{
                    Intent intent = new Intent(Navigation.this, GetStarted.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation.this, NavigationHome.class);
                startActivity(intent);
                finish();
            }
        });

        slideViewPager = findViewById(R.id.slideViewPager);
        dotIndicator = findViewById(R.id.dotIndicador);

        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);

        setDotIndicato(0);
        slideViewPager.addOnPageChangeListener(viewPageListener);


    }

    public void setDotIndicato(int position){
        dots = new TextView[3];

        dotIndicator.removeAllViews();

        for (int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_COMPACT));
            dots [i].setTextSize(35);
            dots [i].setTextColor(getResources().getColor(R.color.gray, getApplicationContext().getTheme()));
            dotIndicator.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.botones, getApplicationContext().getTheme()));
    }

    private int getItem(int i){
        return slideViewPager.getCurrentItem()+i;
    }
}