package com.example.actividad9_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import java.util.Arrays;
import java.util.List;

public class Fragmento1 extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento1_layout, container, false);
        ListView listView = view.findViewById(R.id.mi_lista);
        List<String> datos = Arrays.asList("Elemento 1", "Elemento 2", "Elemento 3");
        Adaptador adaptador = new Adaptador(getActivity(), datos);
        listView.setAdapter(adaptador);
        return view;
    }
}
