package com.example.ejercicio_propuesta_5_3;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button boton1 = findViewById(R.id.bt1);
        Button boton2 = findViewById(R.id.bt2);
        Button boton3 = findViewById(R.id.bt3);
        Button boton4 = findViewById(R.id.bt4);
        Button boton5 = findViewById(R.id.bt5);
        Button boton6 = findViewById(R.id.bt6);
        Button boton7 = findViewById(R.id.bt7);
        Button boton8 = findViewById(R.id.bt8);
        Button boton9 = findViewById(R.id.bt9);
        Button boton10 = findViewById(R.id.bt10);
        Button boton11 = findViewById(R.id.bt11);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_infinite);
                imageView.startAnimation(animation);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_gradual);
                imageView.startAnimation(animation);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.escalado_imagen);
                imageView.startAnimation(animation);
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.escalado_rebote);
                imageView.startAnimation(animation);
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.mueve_1);
                imageView.startAnimation(animation);
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.mueve_2);
                imageView.startAnimation(animation);
            }
        });

        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotar_1);
                imageView.startAnimation(animation);
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotar_2);
                imageView.startAnimation(animation);
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotar_3);
                imageView.startAnimation(animation);
            }
        });

        boton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.varios_1);
                imageView.startAnimation(animation);
            }
        });

        boton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.varios_2);
                imageView.startAnimation(animation);
            }
        });
    }
}
