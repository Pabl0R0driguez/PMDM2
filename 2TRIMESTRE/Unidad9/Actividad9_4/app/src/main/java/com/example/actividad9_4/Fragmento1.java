package com.example.actividad9_4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fragmento1 extends ListFragment {
    private Callbacks mCallbacks = Callbacks.CallbacksVacios;

    public Fragmento1() {
        // Constructor vacío necesario.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragmento1_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurando el adaptador desde `onViewCreated`
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                Contenido.Lista_entrada item = (Contenido.Lista_entrada) entrada;

                TextView textoTitulo = view.findViewById(R.id.textoTitulo);
                TextView textoDescripcion = view.findViewById(R.id.textoDescripcion);
                ImageView imagenEntrada = view.findViewById(R.id.imagenEntrada);

                if (textoTitulo != null) {
                    textoTitulo.setText(item.getTextoTitulo());
                }
                if (textoDescripcion != null) {
                    textoDescripcion.setText(item.getTextoDescripcion());
                }
                if (imagenEntrada != null) {
                    imagenEntrada.setImageResource(item.getIdImagen());
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (Callbacks) activity;
            Log.d("Fragmento1", "Callback configurado correctamente.");
        } catch (ClassCastException e) {
            Log.d("Fragmento1", "Error de configuración del callback.");
            throw new ClassCastException(activity.toString() + " debe implementar la interfaz Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = Callbacks.CallbacksVacios;
        Log.d("Fragmento1", "Callback desvinculado.");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("Fragmento1", "Elemento tocado en la posición: " + position);
        if (mCallbacks != null) {
            Log.d("Fragmento1", "mCallbacks está inicializado");
            mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).getId());
        } else {
            Log.d("Fragmento1", "mCallbacks NO está inicializado");
        }
    }

    public interface Callbacks {
        void onEntradaSeleccionada(String id);

        Callbacks CallbacksVacios = new Callbacks() {
            @Override
            public void onEntradaSeleccionada(String id) {
                // Implementación vacía.
                Log.d("Fragmento1", "Callback vacío llamado con id: " + id);
            }
        };
    }
}
