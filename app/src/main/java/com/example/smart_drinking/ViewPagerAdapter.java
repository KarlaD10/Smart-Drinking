package com.example.smart_drinking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import cjh.WaveProgressBarlibrary.WaveProgressBar;

public class ViewPagerAdapter extends PagerAdapter{

    Context context;

    int[] sliderAllImages = {R.drawable.home_image, R.drawable.historic_image, R.drawable.setting_image};
    int[] sliderAllTitle = {R.string.screen1, R.string.screen2, R.string.screen3, R.string.screen4};
    int[] sliderAllDescriptions = {R.string.narracion_home_tuto,R.string.narracion_home_tuto1, R.string.narracion_home_tuto2, R.string.narracion_home_tuto3};


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
        TextView tv_tutorial_home_tuto;

        switch(position) {
            case 0:
                view = layoutInflater.inflate(R.layout.activity_navigation_home, container, false);

                break;
            case 1:
                final int[] cont = {0};
                view = layoutInflater.inflate(R.layout.fragment_home_tuto, container, false);

                LinearLayout layout_home_tutorial = view.findViewById(R.id.layout_home_tutorial);
                TextView mensaje1_tuto = view.findViewById(R.id.mensaje1_tuto);
                TextView textoProgreso_tuto = view.findViewById(R.id.textoProgreso_tuto);
                WaveProgressBar waveprogressbar_tuto = view.findViewById(R.id.waveprogressbar_tuto);
                LinearLayout layout_registro_tuto = view.findViewById(R.id.layout_registro_tuto);
                tv_tutorial_home_tuto = view.findViewById(R.id.tv_tutorial_home_tuto);


                mensaje1_tuto.setVisibility(View.GONE);
                textoProgreso_tuto.setVisibility(View.GONE);
                waveprogressbar_tuto.setVisibility(View.GONE);
                layout_registro_tuto.setVisibility(View.GONE);

                layout_home_tutorial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (cont[0]){
                            case 0:
                                mensaje1_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto1);
                                cont[0]++;
                                break;
                            case 1:
                                textoProgreso_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto2);
                                cont[0]++;
                                break;
                            case 2:
                                waveprogressbar_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto);
                                cont[0]++;
                                break;
                            case 3:
                                layout_registro_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto3);
                                cont[0]++;
                                break;
                            default:
                                cont[0] =0;
                                break;

                        }
                    }
                });

                break;
            case 2:
                final int[] contHistoric = {0};
                view = layoutInflater.inflate(R.layout.fragment_historic_tuto, container, false);

                switch (contHistoric[0]){
                    case 0:

                        contHistoric[0]++;
                        break;
                    case 1:

                        contHistoric[0]++;
                        break;
                    case 2:

                        contHistoric[0]++;
                        break;
                    case 3:
                        contHistoric[0]++;
                        break;
                    default:
                        contHistoric[0] =0;
                        break;

                }
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
