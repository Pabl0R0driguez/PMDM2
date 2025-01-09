package com.example.ejercicio_propuesto_5_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        Button rojo_boton = findViewById(R.id.buttonRed);
        Button azul_boton = findViewById(R.id.buttonBlue);

        rojo_boton.setOnClickListener(v -> textView.setTextColor(Color.RED));
        azul_boton.setOnClickListener(v -> textView.setTextColor(Color.BLUE));
    }
}
