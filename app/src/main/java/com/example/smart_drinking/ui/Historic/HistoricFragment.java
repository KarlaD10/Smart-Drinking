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


public class HistoricFragment extends Fragment {

    TextView tv_consumoMensual, tv_consumoSemanal;
    CalendarView calendarView;
    DataHelper db;


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
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String formattedDate = String.format("%02d%02d%04d", day, month + 1, year);
                int consumo = db.readConsumoDia(formattedDate);
                if(consumo == -1){
                    Toast.makeText(getActivity(), "Ups parece que no has tomado agua aun", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"Tu consumo de este dia fue de " + consumo, Toast.LENGTH_SHORT).show();
                }
            }
        });
        float progeso =  db.readWeeklyIntake();
        tv_consumoMensual.setText(""+progeso);
    }
}