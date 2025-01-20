package com.example.pablorodriguezpruebatema9;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Agregar los fragmentos a los contenedores
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragmentoEntrada, new FragmentoEntradaTexto())
                .replace(R.id.contenedorFragmentoMostrar, new FragmentoMostrarTexto())
                .commit();
    }
}
