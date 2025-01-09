package com.example.actividad9_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton botonFlotante;
    private boolean esFragmento1Visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonFlotante = findViewById(R.id.botonFlotante);

        // Cargar el primer fragmento al inicio
        cargarFragmento(new Fragmento_1());

        botonFlotante.setOnClickListener(v -> {
            if (esFragmento1Visible) {
                cargarFragmento(new Fragmento_2()); // Cargar el segundo fragmento
            } else {
                cargarFragmento(new Fragmento_1()); // Cargar el primer fragmento
            }
            esFragmento1Visible = !esFragmento1Visible; // Alternar el estado
        });
    }

    // Método para cargar un fragmento
    private void cargarFragmento(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragmentos, fragment);
        transaction.commit();
    }
}
