package com.example.act5_1;

// Importaciones necesarias
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Interfaz2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear un TextView dinámicamente
        TextView textView = new TextView(this);

        // Establecer el texto
        textView.setText("Texto creado en Java con tamaño 20dp, italic y color azul");

        // Establecer el tamaño del texto (20dp)
        textView.setTextSize(20);

        // Establecer el estilo del texto (italic)
        textView.setTypeface(null, android.graphics.Typeface.ITALIC);

        // Establecer el color del texto (azul)
        textView.setTextColor(Color.BLUE);

        // Establecer el TextView como la vista principal
        setContentView(textView);
    }
}