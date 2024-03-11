package com.example.smart_drinking.ui.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smart_drinking.DataBase.DataHelper;
import com.example.smart_drinking.R;

import java.util.Random;

import cjh.WaveProgressBarlibrary.WaveProgressBar;


public class HomeFragment extends Fragment {
    int progress = 0;
    boolean started = false;
    Random randomNumbers = new Random();

    TextView mensaje1, textoProgreso;
    WaveProgressBar waveProgressBar;
    EditText et_registro;
    Button btn_registrar;
    DataHelper db;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DataHelper(getActivity());
        mensaje1 = view.findViewById(R.id.mensaje1);
        textoProgreso = view.findViewById(R.id.textoProgreso);
        waveProgressBar = view.findViewById(R.id.waveprogressbar);
        btn_registrar = view.findViewById(R.id.btn_registrar);
        et_registro = view.findViewById(R.id.et_registro);

        if(getActivity() != null) { // Verifica si la actividad asociada no es nula
            sharedPreferences = getActivity().getSharedPreferences("mensajes", getActivity().MODE_PRIVATE);
            // Ejemplo de cómo usar sharedPreferences (esto dependerá de tu lógica específica)
            int key = randomNumbers.nextInt(10) + 1; // Modificado para usar Random directamente
            String text = sharedPreferences.getString(String.valueOf(key), null);
            mensaje1.setText(text); // Mostrar el mensaje obtenido en el TextView
        }
        waveProgressBar.setProgress(102);
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if (started) {
//                    progress++;
//                    progress++;
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            waveProgressBar.setProgress(progress);
//                        }
//                    });
//
//                    if (progress == 100) {
//                        progress = 0;
//                    }
//                }
//            }
//        };
//
//        timer.schedule(timerTask, 0, 80);
//
//        waveProgressBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                started = !started;
//            }
//        });


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato_consumo = et_registro.getText().toString();

                if (!dato_consumo.isEmpty()){
                    long result = db.addRegistros(dato_consumo);
                    if(result != -1 ){
                        Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        et_registro.setText("");
                        et_registro.setFocusable(false);
                        InputMethodManager imm = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        int progeso =  db.readProgreso();
                        textoProgreso.setText(""+progeso);
                    }
                }else {
                    Toast.makeText(getActivity(), "El dato no es válido. \n Ingresa un dato y vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        int progeso =  db.readProgreso();
        textoProgreso.setText(""+progeso);

    }
}