package com.example.ejercicio_propuesto_5_2;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Asegúrate de que este sea el nombre de tu layout

// Encontrar el ToggleButton por su ID
        ToggleButton toggleButton = findViewById(R.id.toggle_button);
// Configurar un listener para el ToggleButton
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// Mostrar un Toast según el estado del ToggleButton
                if (isChecked) {
                    Toast.makeText(MainActivity2.this, "Encendido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Apagado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}