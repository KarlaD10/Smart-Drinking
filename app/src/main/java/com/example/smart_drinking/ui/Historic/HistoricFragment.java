package com.example.smart_drinking.ui.Historic;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_drinking.DataBase.DataHelper;
import com.example.smart_drinking.R;
import com.example.smart_drinking.ToastCaller;


public class HistoricFragment extends Fragment {

    TextView tv_consumoMensual, tv_consumoSemanal;
    CalendarView calendarView;
    DataHelper db;
    ToastCaller toastCaller = new ToastCaller();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DataHelper(getActivity());

        calendarView = view.findViewById(R.id.calendarView);

        tv_consumoMensual = view.findViewById(R.id.tv_consumoMensual);
        tv_consumoSemanal = view.findViewById(R.id.tv_consumoSemanal);
        LayoutInflater inflaterr = getLayoutInflater();
        View layout = inflaterr.inflate(R.layout.custom_toast, (ViewGroup)view.findViewById(R.id.toast_id));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String formattedDate = String.format("%02d%02d%04d", day, month + 1, year);
                int consumo = db.readConsumoDia(formattedDate);

                float resultado = (float) ((consumo ) / 1000);
                String resultadoConDosDecimales = String.format("%.2f", resultado);

// Si necesitas el resultado como un float para operaciones posteriores:
                float resultadoFinal = Float.parseFloat(resultadoConDosDecimales);
                if(consumo == 0){
                    toastCaller.callToast(layout, getActivity().getApplicationContext(),"Ups parece que no has tomado agua aun");

                }else{
                    toastCaller.callToast(layout, getActivity().getApplicationContext(),"Tu consumo de este dia fue de " + resultadoConDosDecimales + " litros");
                }
            }
        });
        //ggg

        tv_consumoSemanal.setText("¡Tu consumo semanal es de alrededor de 2.3 litros de agua al día!");
        tv_consumoMensual.setText("¡Tu consumo mensual es de aproximadamente 2.6 litros de agua al día!");
    }
}