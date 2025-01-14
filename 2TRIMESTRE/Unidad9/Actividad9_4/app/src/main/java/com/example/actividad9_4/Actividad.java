package com.example.actividad9_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

public class Actividad extends FragmentActivity implements Fragmento1.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
    }

    @Override
    public void onEntradaSeleccionada(String id) {
        // Agrega aquí el código que deseas ejecutar al seleccionar un elemento.
        // Puse un Toast para mostrar un mensaje temporal.
        Toast.makeText(this, "TOCADO EL " + id, Toast.LENGTH_SHORT).show();
            Intent detalleIntent = new Intent(this, Fragmento2.class);
            startActivity(detalleIntent);

        }
    }

