package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText visor;

    // Declara los botones
    Button boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9, boton10, boton11, boton12, boton13, boton14, boton15, boton16, boton17, boton18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el visor
        visor = findViewById(R.id.visor);

        // Inicializa los botones
        boton1 = findViewById(R.id.button_0);
        boton2 = findViewById(R.id.button_1);
        boton3 = findViewById(R.id.button_2);
        boton4 = findViewById(R.id.button_3);
        boton5 = findViewById(R.id.button_4);
        boton6 = findViewById(R.id.button_5);
        boton7 = findViewById(R.id.button_6);
        boton8 = findViewById(R.id.button_7);
        boton9 = findViewById(R.id.button_8);
        boton10 = findViewById(R.id.button_9);
        boton11 = findViewById(R.id.button_decimal);
        boton12 = findViewById(R.id.button_add);
        boton13 = findViewById(R.id.button_subtract);
        boton14 = findViewById(R.id.button_multiply);
        boton15 = findViewById(R.id.button_divide);
        boton16 = findViewById(R.id.button_power);
        boton17 = findViewById(R.id.button_igual);

        // Establece listeners para cada botón
        boton1.setOnClickListener(v -> addToVisor("7"));
        boton2.setOnClickListener(v -> addToVisor("8"));
        boton3.setOnClickListener(v -> addToVisor("9"));
        boton4.setOnClickListener(v -> addToVisor("/"));
        boton5.setOnClickListener(v -> addToVisor("4"));
        boton6.setOnClickListener(v -> addToVisor("5"));
        boton7.setOnClickListener(v -> addToVisor("6"));
        boton8.setOnClickListener(v -> addToVisor("*"));
        boton9.setOnClickListener(v -> addToVisor("1"));
        boton10.setOnClickListener(v -> addToVisor("2"));
        boton11.setOnClickListener(v -> addToVisor("3"));
        boton12.setOnClickListener(v -> addToVisor("-"));
        boton13.setOnClickListener(v -> addToVisor("0"));
        boton14.setOnClickListener(v -> addToVisor("."));
        boton15.setOnClickListener(v -> addToVisor("*"));
        boton16.setOnClickListener(v -> addToVisor("*"));
    }

    // Método para añadir texto al visor
    private void addToVisor(String text) {
        visor.append(text);
    }


}
