package com.example.ejercicio_propuesto_5_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_2 extends AppCompatActivity {

    private TextView textView;
    private Button botonRojo, botonAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        textView = findViewById(R.id.textView);
        botonRojo = findViewById(R.id.buttonRed);
        botonAzul = findViewById(R.id.buttonBlue);

        botonRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Botón 1 pulsado");
            }
        });

        botonAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Botón 2 pulsado");
            }
        });
    }
}
