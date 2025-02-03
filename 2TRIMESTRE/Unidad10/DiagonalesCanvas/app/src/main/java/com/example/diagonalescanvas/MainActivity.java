package com.example.diagonalescanvas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usar la vista personalizada para dibujar las diagonales
        setContentView(new com.example.diagonalesdelcanvas.DiagonalesView(this));
    }
}
