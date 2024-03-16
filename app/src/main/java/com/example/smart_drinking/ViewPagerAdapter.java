package com.example.smart_drinking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter{

    Context context;

    int[] sliderAllImages = {R.drawable.home_image, R.drawable.historic_image, R.drawable.setting_image};
    int[] sliderAllTitle = {R.string.screen1, R.string.screen2, R.string.screen3};
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
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView sliderImage = view.findViewById(R.id.sliderImage);
        TextView sliderTitle = view.findViewById(R.id.sliderTitle);
        TextView sliderDesc = view.findViewById(R.id.slideDesc);

        sliderImage.setImageResource(sliderAllImages[position]);
        sliderTitle.setText(this.sliderAllTitle[position]);
        sliderDesc.setText(this.sliderAllDescriptions[position]);

        container.addView(view );
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
