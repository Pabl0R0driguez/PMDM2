package com.example.ejercicio6;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String cadena = getIntent().getStringExtra("Cadena: ");
        double decimal = getIntent().getDoubleExtra("Decimal: ", 0.0);

        String mensaje = "Cadena : " + cadena + ", Decimal : " + decimal;
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}