package com.example.actividad9_4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragmento3 extends Fragment {

    public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id";

    private Contenido.Lista_entrada mItem;

    public Fragmento3() {
        // Constructor vacío obligatorio
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)) {
            String id = getArguments().getString(ARG_ID_ENTRADA_SELECCIONADA);
            mItem = Contenido.ENT_LISTA_HASHMAP.get(id);

            if (mItem == null) {
                Log.e("DEBUG", "No se encontró el elemento para el ID: " + id);
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textotitulo)).setText(mItem.getTextoTitulo());
            ((TextView) rootView.findViewById(R.id.textocontenido)).setText(mItem.getTextoDescripcion());
            ((ImageView) rootView.findViewById(R.id.imagen)).setImageResource(mItem.getIdImagen());
        }
        return rootView;
    }

}
