package com.example.smart_drinking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
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
                TextView tv_tutorial_home_tuto = view.findViewById(R.id.tv_tutorial_home_tuto);

                layout_home_tutorial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (cont[0]){
                            case 0:
                                mensaje1_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto);
                                cont[0]++;
                                break;
                            case 1:
                                textoProgreso_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto1);
                                cont[0]++;
                                break;
                            case 2:
                                waveprogressbar_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto2);
                                cont[0]++;
                                break;
                            case 3:
                                layout_registro_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_home_tuto.setText(R.string.narracion_home_tuto3);
                                cont[0]++;
                                break;
                            default:
                                //cont[0] =0;
                                break;
                        }
                    }
                });

                break;
            case 2:
                final int[] contHistoric = {0};
                view = layoutInflater.inflate(R.layout.fragment_historic_tuto, container, false);

                CalendarView calendarView_tuto = view.findViewById(R.id.calendarView_tuto);
                TextView tv_consumoMensual_tuto = view.findViewById(R.id.tv_consumoMensual_tuto);
                LinearLayout LinearLayout_tuto = view.findViewById(R.id.LinearLayout_tuto);
                LinearLayout layout_historic_tutorial = view.findViewById(R.id.layout_historic_tutorial);
                TextView tv_tutorial_historic_tuto = view.findViewById(R.id.tv_tutorial_historic);


                layout_historic_tutorial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (contHistoric[0]){
                            case 0:
                                calendarView_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_historic_tuto.setText("Cuando haglas click en algún día, te mostrará el consumo que tuviste en esa fecha");
                                contHistoric[0]++;
                                break;
                            case 1:
                                tv_consumoMensual_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_historic_tuto.setText("Rastrea tu consumo mensual es este texto");
                                contHistoric[0]++;
                                break;
                            case 2:
                                LinearLayout_tuto.setVisibility(View.VISIBLE);
                                tv_tutorial_historic_tuto.setText("Y el semanal en este ");
                                contHistoric[0]++;
                                break;
                            default:
                                //contHistoric[0] =0;
                                break;
                        }
                    }
                });
                break;

            case 3:
                final int[] contAjustes = {0};
                view = layoutInflater.inflate(R.layout.fragment_setting_tuto, container, false);

                TextView tvConectarBluetooth_tuto = view.findViewById(R.id.tvConectarBluetooth_tuto);
                LinearLayout layout_dispositivos = view.findViewById(R.id.layout_dispositivos);
                View view_first = view.findViewById(R.id.view_first);
                TextView tv_configura_aviso = view.findViewById(R.id.tv_configura_aviso);
                LinearLayout layout_vibracion = view.findViewById(R.id.layout_vibracion);
                LinearLayout layout_sonido = view.findViewById(R.id.layout_sonido);
                View view_second = view.findViewById(R.id.view_second);
                TextView tv_registrar = view.findViewById(R.id.tv_registrar);
                LinearLayout dias_semanales = view.findViewById(R.id.dias_semanales);
                LinearLayout layout_hora_message = view.findViewById(R.id.layout_hora_message);
                LinearLayout layout_seleccion_hora = view.findViewById(R.id.layout_seleccion_hora);
                LinearLayout layout_settings_tuto = view.findViewById(R.id.layout_settings_tuto);
                Button btn_registrar = view.findViewById(R.id.btn_registrar);
                TextView tv_tutorial_ajustes = view.findViewById(R.id.tv_tutorial_ajustes);


                layout_settings_tuto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (contAjustes[0]){
                            case 0:
                                tvConectarBluetooth_tuto.setVisibility(View.VISIBLE);
                                layout_dispositivos.setVisibility(View.VISIBLE);
                                view_first.setVisibility(View.VISIBLE);
                                tv_tutorial_ajustes.setText("Conecta tu dispositivo Smart Drink para una mejor funcionalidad");

                                contAjustes[0]++;
                                break;
                            case 1:
                                tv_configura_aviso.setVisibility(View.VISIBLE);
                                layout_vibracion.setVisibility(View.VISIBLE);
                                layout_sonido.setVisibility(View.VISIBLE);
                                view_second.setVisibility(View.VISIBLE);
                                tv_tutorial_ajustes.setText("Modifica la forma en la que quieres que se presenten los recordatorios");

                                contAjustes[0]++;
                                break;
                            case 2:
                                tv_registrar.setVisibility(View.VISIBLE);
                                dias_semanales.setVisibility(View.VISIBLE);
                                tv_tutorial_ajustes.setText("Selecciona los días específicos para recordarte el tomar agua");

                                contAjustes[0]++;
                                break;
                            case 3:
                                layout_hora_message.setVisibility(View.VISIBLE);
                                layout_seleccion_hora.setVisibility(View.VISIBLE);
                                tv_tutorial_ajustes.setText("Selecciona las horas específicas para recordarte el tomar agua ");
                                contAjustes[0]++;
                                break;
                            case  4:
                                tv_tutorial_ajustes.setText("Finalmente podrás registrar esa configuración de recordatorio ");
                                btn_registrar.setVisibility(View.VISIBLE);

                                contAjustes[0]++;
                            default:
                                //contAjustes[0] =0;
                                break;
                        }
                    }
                });
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
