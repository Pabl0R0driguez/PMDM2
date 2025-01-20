package com.example.pablorodriguezpruebatema9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentoMostrarTexto extends Fragment {

    private ModeloCompartido modeloCompartido;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_display, container, false);

        // Inicializar el ViewModel compartido
        modeloCompartido = new ViewModelProvider(requireActivity()).get(ModeloCompartido.class);

        TextView textoMostrado = vista.findViewById(R.id.displayTextView);

        // Observar cambios en el texto del ViewModel
        modeloCompartido.getTexto().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String texto) {
                textoMostrado.setText(texto);
            }
        });

        return vista;
    }
}
