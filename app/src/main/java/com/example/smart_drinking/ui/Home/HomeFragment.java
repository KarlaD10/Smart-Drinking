package com.example.smart_drinking.ui.Home;
import java.util.Random;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smart_drinking.R;


public class HomeFragment extends Fragment {
    Random randomNumbers = new Random();

    TextView mensaje1;
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

        mensaje1 = view.findViewById(R.id.mensaje1);
        if(getActivity() != null) { // Verifica si la actividad asociada no es nula
            sharedPreferences = getActivity().getSharedPreferences("mensajes", getActivity().MODE_PRIVATE);
            // Ejemplo de cómo usar sharedPreferences (esto dependerá de tu lógica específica)
            int key = randomNumbers.nextInt(10) + 1; // Modificado para usar Random directamente
            String text = sharedPreferences.getString(String.valueOf(key), null);
            mensaje1.setText(text); // Mostrar el mensaje obtenido en el TextView
        }
    }
}