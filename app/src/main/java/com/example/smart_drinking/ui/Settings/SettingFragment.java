package com.example.smart_drinking.ui.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
//a
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

import com.example.smart_drinking.R;
import com.example.smart_drinking.recyclerView.RM_RecyclerViewAdapter;
import com.example.smart_drinking.recyclerView.RemindersModel;

import java.util.ArrayList;


public class SettingFragment extends Fragment {
    ArrayList<RemindersModel> reminderModels = new ArrayList<>();
    Switch swt_sonido, swt_vibracion;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<String> diasSeleccionados = new ArrayList<>();
    Button btn_lunes, btn_martes, btn_miercoles, btn_jueves, btn_viernes, btn_sabado, btn_domingo, btn_registrar;
    NumberPicker numPickerH, numPickerM,numPickerAm;
    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        // Return the inflated view
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swt_sonido = view.findViewById(R.id.swt_sonido);
        swt_vibracion = view.findViewById(R.id.swt_vibracion);
        btn_lunes = view.findViewById(R.id.btn_lunes);
        btn_martes = view.findViewById(R.id.btn_martes);
        btn_miercoles = view.findViewById(R.id.btn_miercoles);
        btn_jueves = view.findViewById(R.id.btn_jueves);
        btn_viernes = view.findViewById(R.id.btn_viernes);
        btn_sabado = view.findViewById(R.id.btn_sabado);
        btn_domingo = view.findViewById(R.id.btn_domingo);
        btn_registrar = view.findViewById(R.id.btn_registrar);
        numPickerH = view.findViewById(R.id.numPickerH);
        numPickerM  = view.findViewById(R.id.numPickerM);
        numPickerAm = view.findViewById(R.id.numPickerAm);
        mp = MediaPlayer.create(getActivity(), R.raw.notificacion);

        initAllPicker();
        // Now you can find your RecyclerView in this view
        RecyclerView myRecyclerView = view.findViewById(R.id.myRecyclerView);
        setUpReminderModels();
        sharedPreferences = getActivity().getSharedPreferences("notificaciones", getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("notificaciones",getActivity().MODE_PRIVATE);

        swt_sonido.setChecked(sharedPreferences.getBoolean("sonido",false));
        swt_vibracion.setChecked(sharedPreferences.getBoolean("vibracion",false));


//        sharedPreferences = getActivity().getSharedPreferences("notificaciones", getActivity().MODE_PRIVATE);

        swt_sonido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("sonido",swt_sonido.isChecked());
                editor.apply();
                if (swt_sonido.isChecked()){
                    mp.start();

                }

            }
        });
        swt_vibracion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("vibracion",swt_vibracion.isChecked());
                editor.apply();

                if (swt_vibracion.isChecked()){
                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                }
            }
        });
        // Set up the RecyclerView adapter and layout manager
        RM_RecyclerViewAdapter adapter = new RM_RecyclerViewAdapter(getContext(), reminderModels); // Change 'this' to 'getContext()'
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Change 'this' to 'getContext()

        btn_lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_lunes, "Lunes");
            }
        });
        btn_martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_martes, "Martes");
            }
        });
        btn_miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_miercoles, "Miercoles");
            }
        });
        btn_jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_jueves, "Jueves");
            }
        });
        btn_viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_viernes, "Viernes");
            }
        });
        btn_sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_sabado, "Sabado");
            }
        });
        btn_domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(btn_domingo, "Domingo");
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imprimirDiasSeleccionados();
            }
        });

    }

    public void initPicker(int min, int max, NumberPicker p){
        p.setMinValue(min);
        p.setMaxValue(max);
        p.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });
    }

    public void initPickerWithString(int min, int max, NumberPicker p, String[] str){
        p.setMinValue(min);
        p.setMaxValue(max);
        p.setDisplayedValues(str);
    }

    public void initAllPicker(){
        String[] str = {"AM", "PM"};

        // Asumiendo que numPickerH, numPickerM, numPickerAm, etc., son variables de instancia de NumberPicker.
        initPicker(0, 12, numPickerH);
        initPicker(0, 59, numPickerM);
        initPickerWithString(0, str.length - 1, numPickerAm, str);

    }



    private void toggleSelection(Button button, String dia) {
        if (diasSeleccionados.contains(dia)) {
            diasSeleccionados.remove(dia);
        } else {
            diasSeleccionados.add(dia);
            button.setAlpha(0.5f); // Cambiar la opacidad para indicar selección
        }
    }

    private void imprimirDiasSeleccionados() {
        StringBuilder mensaje = new StringBuilder("Días seleccionados: ");
//        button.setAlpha(1f);
        for (String dia : diasSeleccionados) {
            mensaje.append(dia).append(", ");
        }
        // Eliminar la coma extra y mostrar el mensaje
        mensaje.deleteCharAt(mensaje.length() - 2);
        // Puedes imprimir o mostrar el mensaje en un TextView, por ejemplo
        System.out.println(mensaje.toString());
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