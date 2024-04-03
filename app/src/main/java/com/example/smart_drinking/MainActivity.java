package com.example.smart_drinking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//a

    //Variables for animation
    SharedPreferences sharedPreferences;
    private static final String uno = "Tu cuerpo agradece cada sorbo de agua.";
    private static final String dos = "Cada gota cuenta, ¡hidrátate!";
    private static final String tres = "El agua purifica cuerpo y alma.";
    private static final String cuatro ="Más agua, más energía, más vida.";
    private static final String cinco = "Renueva tu energía, bebe agua." ;
    private static final String seis = "Hidratación = Felicidad plena.";
    private static final String siete = "Agua diaria, salud para tu vida." ;
    private static final String  ocho = "Refresca tu mente con agua pura." ;
    private static final String  nueve = "Siente el poder de cada gota." ;
    private static final String  diez = "Beber agua es abrazar la vida.";
    private static final String key1 = "1";
    private static final String key2 = "2";
    private static final String key3 = "3";
    private static final String key4 = "4";
    private static final String key5 = "5";
    private static final String key6 = "6";
    private static final String key7= "7";
    private static final String key8 = "8";
    private static final String key9 = "9";
    private static final String key10 = "10";
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.botton_animation);

        image = findViewById(R.id.gota);
        logo =  findViewById(R.id.loguitp);
        slogan =  findViewById(R.id.slogan);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);


        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.botton_animation);



        // Asignar el AnimationListener a bottomAnim
        bottomAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Acciones al iniciar la animación, si es necesario
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Este código se ejecuta después de que la animación haya terminado
                sharedPreferences = getSharedPreferences("mensajes", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(key1,uno);
                editor.putString(key2,dos);
                editor.putString(key3,tres);
                editor.putString(key4,cuatro);
                editor.putString(key5,cinco);
                editor.putString(key6,seis);
                editor.putString(key7,siete);
                editor.putString(key8,ocho);
                editor.putString(key9,nueve);
                editor.putString(key10,diez);
                editor.apply();
                Intent intent = new Intent(MainActivity.this, NavigationHome.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Acciones en cada repetición de la animación, si es necesario
            }
        });

        // Iniciar la animación
        slogan.setAnimation(bottomAnim);
    }

}