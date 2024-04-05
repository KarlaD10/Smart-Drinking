package com.example.smart_drinking.ui.Home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smart_drinking.DataBase.DataHelper;
import com.example.smart_drinking.R;
import com.example.smart_drinking.ToastCaller;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;
import java.util.Random;

import cjh.WaveProgressBarlibrary.WaveProgressBar;


public class HomeFragment extends Fragment {
    int progress = 0;
    boolean started = false;
    Random randomNumbers = new Random();
    TextView tv_toast;
    TextView mensaje1, textoProgreso;
    WaveProgressBar waveProgressBar;
    EditText et_registro;
    Button btn_registrar;
    DataHelper db;
    SharedPreferences sharedPreferences;
    MqttAndroidClient client;
    ToastCaller toastCaller = new ToastCaller();
    IMqttToken token;
    String bebidas[] = {"1","2","3","4","5"};


    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
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

        LayoutInflater inflaterr = getLayoutInflater();
        View layout = inflaterr.inflate(R.layout.custom_toast, (ViewGroup)view.findViewById(R.id.toast_id));

       //#tv_toast = (TextView) layout.findViewById(R.id.tv_toast);
        //final Toast tostada = new Toast(getActivity().getApplicationContext());
        //tostada.setGravity(Gravity.CENTER_VERTICAL,0,0);
        //tostada.setDuration(Toast.LENGTH_LONG);
        //tostada.setView(layout);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(getActivity(), "tcp://test.mosquitto.org:1883", clientId);

        try {
            token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    try {
                        client.subscribe("Smartdrink/Mensaje", 0);
                    } catch (MqttException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(getActivity(), "Error en la conexión", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            System.out.println("Ecepcion en mqtt" + e);
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                String mensaje = new String(message.getPayload());
                if (topic.matches("Smartdrink/Mensaje")){
                    if (!mensaje.isEmpty()){

                        long result = db.addRegistros(mensaje);
                        if(result != -1 ) {

                            toastCaller.callToast(layout, getActivity().getApplicationContext(),"Registro exitoso");
                            et_registro.setText("");

                            int progeso = db.readProgreso();
                            int value = (int) ((progeso * 100) / 2000);
                            mensaje = (2 >= progeso / 1000) ? " litros tomados" : " litro tomado";
                            textoProgreso.setText("Bien hecho, llevas " + (float) progeso / 1000 + mensaje);

                            waveProgressBar.setProgress(value);
                        }

                    }
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        if(getActivity() != null) { // Verifica si la actividad asociada no es nula
            sharedPreferences = getActivity().getSharedPreferences("mensajes", getActivity().MODE_PRIVATE);
            // Ejemplo de cómo usar sharedPreferences (esto dependerá de tu lógica específica)
            int key = randomNumbers.nextInt(10) + 1; // Modificado para usar Random directamente
            String text = sharedPreferences.getString(String.valueOf(key), null);
            mensaje1.setText(text); // Mostrar el mensaje obtenido en el TextView
        }
        int progeso =  db.readProgreso();
        String mensaje = (2 >= progeso/1000) ? " litros tomados" : " litro tomado";
        if(  progeso <= 0 ) {
            textoProgreso.setText("Parece que no has tomado agua, ¡toma un poco para subir ese progreso!");
        }
        else{
            textoProgreso.setText("Bien hecho, llevas "+(float) progeso/1000 + mensaje);
        }
        int value = (int) ((progeso*100)/2000);
        waveProgressBar.setProgress(value);
        mensaje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acción a realizar cuando el TextView es clickeado
                Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                db.addRegistros("50");
                int progeso =  db.readProgreso();
                int value = (int) ((progeso*100)/2000);
                String mensaje = (2 >= progeso/1000) ? " litros tomados" : " litro tomado";
                textoProgreso.setText("Bien hecho, llevas "+(float)progeso/1000 + mensaje);

                waveProgressBar.setProgress(value);
            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato_consumo = et_registro.getText().toString();

                if (!dato_consumo.isEmpty()){
                    long result = db.addRegistros(dato_consumo);
                    if(result != -1 ){
                        //Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        //tv_toast.setText("Registro exitoso");
                        //tostada.show();
                        toastCaller.callToast(layout, getActivity().getApplicationContext(),"Registro exitoso");
                        et_registro.setText("");

                        InputMethodManager imm = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        int progeso =  db.readProgreso();
                        int value = (int) ((progeso*100)/2000);
                        String mensaje = (2 >= progeso/1000) ? " litros tomados" : " litro tomado";
                        textoProgreso.setText("Bien hecho, llevas "+(float)progeso/1000 + mensaje);

                        waveProgressBar.setProgress(value);
                    }
                }else {
                    Toast.makeText(getActivity(), "El dato no es válido. \n Ingresa un dato y vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}