package com.example.actividad9_4;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class Fragmento2 extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_unpanel);

        if (savedInstanceState == null) {
            // Crear el Bundle con los argumentos
            Bundle arguments = new Bundle();
            arguments.putString(
                    Fragmento3.ARG_ID_ENTRADA_SELECCIONADA,
                    getIntent().getStringExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA)
            );

            // Crear el Fragmento3 y pasarle los argumentos
            Fragmento3 fragmentoDetalle = new Fragmento3();
            fragmentoDetalle.setArguments(arguments);

            // Añadir el fragmento al contenedor
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmento_contenedor, fragmentoDetalle)
                    .commit();
        }
    }
}
