package com.example.actividad9_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;

public class Actividad extends FragmentActivity implements Fragmento1.Callbacks{

    private boolean dosFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dospaneles);
        Contenido.inicializarDatos(); // Inicializa los datos aquí


        // Verificar si estamos en una tablet
        if (findViewById(R.id.frame_contenedor) != null) {
            dosFragmentos = true; // Estamos en una tablet
        } else {
            dosFragmentos = false; // Estamos en un dispositivo normal
        }
    }


    @Override
    public void onEntradaSeleccionada(String id) {
        Log.d("DEBUG", "ID recibido en Actividad: " + id);

        if (dosFragmentos) {
            Fragmento3 fragmentoDetalle = new Fragmento3();
            Bundle argumentos = new Bundle();
            argumentos.putString(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            fragmentoDetalle.setArguments(argumentos);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_contenedor, fragmentoDetalle)
                    .commit();
            Log.d("DEBUG", "Fragmento3 reemplazado en tablet");
        } else {
            Intent detalleIntent = new Intent(this, Fragmento2.class);
            detalleIntent.putExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            startActivity(detalleIntent);
            Log.d("DEBUG", "Actividad Fragmento2 iniciada");
        }
    }


}

