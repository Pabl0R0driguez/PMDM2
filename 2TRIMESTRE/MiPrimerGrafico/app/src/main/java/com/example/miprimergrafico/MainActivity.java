package com.example.miprimergrafico;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usar la vista personalizada para el gráfico
        setContentView(new GraficoView(this));
    }
}
