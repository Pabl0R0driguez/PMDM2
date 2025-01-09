package android.example.aplicaciongestion;

public class Elemento {
    private String imagenUrl;
    private String titulo;
    private String contenido;
    private float puntuacion;
    private String direccionWeb;
    private String telefono;

    // Constructor
    public Elemento(String imagenUrl, String titulo, int contenido, float puntuacion, String telefono) {
        this.imagenUrl = imagenUrl;
        this.titulo = titulo;
        this.contenido = String.valueOf(contenido);
        this.puntuacion = puntuacion;
        this.direccionWeb = direccionWeb;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public String getDireccionWeb() {
        return direccionWeb;
    }

    public String getTelefono() {
        return telefono;
    }
}
