package com.example.ejercicio6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.botonEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = "Hola"; //
                double decimal = 3.14; //
                Intent actividad2 = new Intent(MainActivity.this, MainActivity2.class);
                actividad2.putExtra("Cadema: ", cadena);
                actividad2.putExtra("Decimal: ", decimal);

                startActivity(actividad2);
            }
        });
    }
}