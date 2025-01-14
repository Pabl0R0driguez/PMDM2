package com.example.actividad9_4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido extends Fragment {

    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<>();
    public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño para este fragmento
        View view = inflater.inflate(R.layout.fragmento1_layout, container, false); // Inflar con fragmento1_layout

        // Inicializar los datos
        inicializarDatos();

        Adaptador adaptador = new Adaptador(getActivity(), R.layout.layout_listado, ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                Lista_entrada item = (Lista_entrada) entrada;
                TextView textoTitulo = view.findViewById(R.id.textotitulo);
                ImageView imagenLista = view.findViewById(R.id.imagen);

                if (textoTitulo != null) {
                    textoTitulo.setText(item.getTextoTitulo());
                }
                if (imagenLista != null) {
                    imagenLista.setImageResource(item.getIdImagen());
                }
            }
        };

        ListView lista = view.findViewById(android.R.id.list);
        lista.setAdapter(adaptador);

        return view;
    }

    // Clase interna Lista_entrada
    public static class Lista_entrada {
        private String id;
        private int idImagen;
        private String textoTitulo;
        private String textoDescripcion;

        public Lista_entrada(String id, int idImagen, String textoTitulo, String textoDescripcion) {
            this.id = id;
            this.idImagen = idImagen;
            this.textoTitulo = textoTitulo;
            this.textoDescripcion = textoDescripcion;
        }

        public String getId() {
            return id;
        }

        public int getIdImagen() {
            return idImagen;
        }

        public String getTextoTitulo() {
            return textoTitulo;
        }

        public String getTextoDescripcion() {
            return textoDescripcion;
        }
    }

    // Método para añadir entradas a la lista y al HashMap
    private static void ponerEntrada(Lista_entrada entrada) {
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(entrada.getId(), entrada);
    }

    // Ejemplo de cómo añadir datos
    public static void inicializarDatos() {
        ponerEntrada(new Lista_entrada("0", R.drawable.ima1, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29. En la actualización se incluyen numerosas características nuevas."));
        ponerEntrada(new Lista_entrada("1", R.drawable.ima2, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32."));
        ponerEntrada(new Lista_entrada("2", R.drawable.ima3, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35."));
        ponerEntrada(new Lista_entrada("3", R.drawable.ima4, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel). Fue la primera actualización exclusiva para TV y tableta, lo que quiere decir que sólo es apta para TV y tabletas y no para teléfonos Android."));
        ponerEntrada(new Lista_entrada("4", R.drawable.ima5, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich (Sándwich de Helado), basado en el núcleo de Linux 3.0.1, fue lanzado públicamente el 12 de octubre de 2011."));
        ponerEntrada(new Lista_entrada("5", R.drawable.ima6, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del 30 de junio de 2012. Basado en el núcleo de linux 3.0.31, Bean fue una actualización incremental con el enfoque primario de mejorar la funcionalidad y el rendimiento de la interfaz de usuario."));
        ponerEntrada(new Lista_entrada("6", R.drawable.ima7, "KITKAT", "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium."));
        ponerEntrada(new Lista_entrada("7", R.drawable.ima8, "LOLLIPOP", "Incluye Material Design, un diseño intrépido, colorido, y sensible interfaz de usuario para las experiencias coherentes e intuitivos en todos los dispositivos. Movimiento de respuesta natural, iluminación y sombras realistas y familiares elementos visuales hacen que sea más fácil de navegar su dispositivo."));
    }
}
