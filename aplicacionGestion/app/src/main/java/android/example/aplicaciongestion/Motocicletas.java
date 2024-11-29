package android.example.aplicaciongestion;

import java.io.Serializable;

public class Motocicletas implements Serializable {
    private String titulo;
    private String contenido;
    private int puntuacion;
    private double precio;
    private int imagenResId;

    // Constructor
    public Motocicletas(String titulo,int puntuacion,  double precio, int imagenResId, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.puntuacion = puntuacion;
        this.precio = precio;
        this.imagenResId = imagenResId;
    }

    // Getters y setters
    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public int getPuntuacion() { return puntuacion; }
    public double getPrecio() { return precio; }
    public int getImagenResId() { return imagenResId; }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }
}
