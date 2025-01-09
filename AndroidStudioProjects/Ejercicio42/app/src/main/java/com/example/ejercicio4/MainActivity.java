package com.example.ejercicio4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste de los insets de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i("Act1", "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Act1", "onDestroy");

        // Iniciar la segunda actividad al destruir la actual
        Intent ejemplo = new Intent(Intent.ACTION_DIAL);
        ejemplo.setData(Uri.parse("Teléfono: 623157362"));
        startActivity(ejemplo);
    }
}