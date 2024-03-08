package com.example.smart_drinking;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variables for animation

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