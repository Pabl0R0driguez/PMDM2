package com.example.pablorodriguezpruebatema9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentoEntradaTexto extends Fragment {

    private ModeloCompartido modeloCompartido;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_input, container, false);

        // Inicializar el ViewModel compartido
        modeloCompartido = new ViewModelProvider(requireActivity()).get(ModeloCompartido.class);

        EditText campoTexto = vista.findViewById(R.id.inputEditText);
        Button botonEnviar = vista.findViewById(R.id.sendButton);

        // Configurar la acción del botón para enviar texto
        botonEnviar.setOnClickListener(v -> {
            String texto = campoTexto.getText().toString();
            if (!texto.isEmpty()) {
                modeloCompartido.setTexto(texto); // Enviar texto al ViewModel
            }
        });

        return vista;
    }
}
