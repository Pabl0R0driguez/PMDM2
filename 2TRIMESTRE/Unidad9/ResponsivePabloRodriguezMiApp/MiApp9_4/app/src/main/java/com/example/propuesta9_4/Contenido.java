package com.example.propuesta9_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {
    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<Lista_entrada>();
    public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();
    public static class Lista_entrada{

        public String titulo;
        public int puntuacion;
        public String contenido;
        public String fecha;
        public int precio;
        public int imagenResId;
        public String id;

        public Lista_entrada(String id,String titulo,int puntuacion,  int precio, int imagenResId, String contenido, String fecha) {
            this.id = id;
            this.titulo = titulo;
            this.contenido = contenido;
            this.puntuacion = puntuacion;
            this.precio = precio;
            this.imagenResId = imagenResId;
            this.fecha = fecha;

        }


    }
    private static void ponerEntrada(Lista_entrada entrada){
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(String.valueOf(entrada.id), entrada);
    }

    static {
        ponerEntrada(new Lista_entrada("1","Ducati Panigale V4", 5, 5000, R.drawable.moto3, "Superbike de alto rendimiento con motor V4 de 1103 cc y 214 caballos de fuerza.", "01/01/2024"));

        ponerEntrada(new Lista_entrada("2","BMW R 1250 GS", 4, 5500, R.drawable.moto7, "Motocicleta de aventura con motor bóxer de 1254 cc, ideal para viajes largos y todo terreno.", "04/08/2007"));

        ponerEntrada(new Lista_entrada("3","Honda CBR650R", 3, 4000, R.drawable.moto5, "Moto deportiva de 649 cc, perfecta para quienes buscan agilidad y rendimiento en carretera.", "03/01/2024"));

        ponerEntrada(new Lista_entrada("4","Suzuki Hayabusa", 5, 6500, R.drawable.moto1, "Motocicleta deportiva de 1340 cc, famosa por su velocidad y rendimiento en pista.", "04/01/2024"));

        ponerEntrada(new Lista_entrada("5","KTM 390 Duke", 3, 3000, R.drawable.moto2, "Moto naked de 373 cc, ligera y ágil, ideal para principiantes y la ciudad.", "05/01/2024"));


    }

}
