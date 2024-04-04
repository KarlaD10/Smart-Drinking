package com.example.smart_drinking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter{

    Context context;

    int[] sliderAllImages = {R.drawable.home_image, R.drawable.historic_image, R.drawable.setting_image};
    int[] sliderAllTitle = {R.string.screen1, R.string.screen2, R.string.screen3, R.string.screen4};
    int[] sliderAllDescriptions = {R.string.screenDescrip1, R.string.screenDescrip2, R.string.screenDescrip3};


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderAllTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;

        switch(position) {
            case 0:
                view = layoutInflater.inflate(R.layout.activity_navigation_home, container, false);

                break;
            case 1:
                view = layoutInflater.inflate(R.layout.fragment_home, container, false);
                break;
            case 2:
                view = layoutInflater.inflate(R.layout.fragment_historic, container, false);
                break;
            case 3:
                view = layoutInflater.inflate(R.layout.fragment_setting, container, false);
                break;
        }

        // Agregar la vista al contenedor
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
