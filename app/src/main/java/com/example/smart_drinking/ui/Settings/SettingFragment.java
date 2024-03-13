package com.example.smart_drinking.ui.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
//a
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.smart_drinking.R;
import com.example.smart_drinking.RM_RecyclerViewAdapter;
import com.example.smart_drinking.RemindersModel;

import java.util.ArrayList;


public class SettingFragment extends Fragment {
    ArrayList<RemindersModel> reminderModels = new ArrayList<>();
    Switch swt_sonido, swt_vibracion;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        swt_sonido = view.findViewById(R.id.swt_sonido);
        swt_vibracion = view.findViewById(R.id.swt_vibración);
        // Now you can find your RecyclerView in this view
        RecyclerView myRecyclerView = view.findViewById(R.id.myRecyclerView);
        setUpReminderModels();
        sharedPreferences = getActivity().getSharedPreferences("notificaciones", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("notificaciones",getActivity().MODE_PRIVATE);

            Toast.makeText(getActivity(), "No Vacio", Toast.LENGTH_SHORT).show();
            swt_sonido.setChecked(sharedPreferences1.getBoolean("sonido",false));
            swt_vibracion.setChecked(sharedPreferences1.getBoolean("vibracion",false));


        sharedPreferences = getActivity().getSharedPreferences("notificaciones", getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        swt_sonido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Toast.makeText(getActivity(), ""+swt_sonido.isChecked(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("sonido",swt_sonido.isChecked());
                editor.apply();
            }
        });
        swt_vibracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("vibracion",swt_vibracion.isChecked());
                editor.apply();
                Toast.makeText(getActivity(), ""+swt_vibracion.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });
        // Set up the RecyclerView adapter and layout manager
        RM_RecyclerViewAdapter adapter = new RM_RecyclerViewAdapter(getContext(), reminderModels); // Change 'this' to 'getContext()'
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Change 'this' to 'getContext()'

        //ignora losiguietne, es para ver algo de git
        int a = 0;
        // Return the inflated view
        return view;

    }

    private void setUpReminderModels(){
        //Aqui debe de ir una forma en la que se obtienen los valores de la BD de los recodatorios;
        //Y se almacenen en formad de String


        for (int i = 0; i < 10; i++){
            reminderModels.add(new RemindersModel(""+i,
                                                    ""+i,
                                                    ""+i,
                                                    R.drawable.baseline_access_alarms_24));
        }
    }
}