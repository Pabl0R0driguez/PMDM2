package com.example.pablorodriguezpruebatema9;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentoEntradaTexto.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragmentoEntrada, new FragmentoEntradaTexto())
                .replace(R.id.contenedorFragmentoMostrar, new FragmentoMostrarTexto())
                .commit();
    }

    @Override
    public void onTextoEnviado(String texto) {
        FragmentoMostrarTexto fragmentoMostrar = (FragmentoMostrarTexto)
                getSupportFragmentManager().findFragmentById(R.id.contenedorFragmentoMostrar);

        if (fragmentoMostrar != null) {
            fragmentoMostrar.actualizarTexto(texto);
        }
    }
}
