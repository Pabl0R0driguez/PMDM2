package com.example.act5_1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Interfaz3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz3);


        // Obtener referencia al TextView desde el XML
        TextView textView = findViewById(R.id.text1view);

        // Establecer el color del texto azul
        textView.setTextColor(Color.BLUE);

        // Añadir texto en azul usando append()
        textView.append("\n y texto añadido desde append");

    }
}