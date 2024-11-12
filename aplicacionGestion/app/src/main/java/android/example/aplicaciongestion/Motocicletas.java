package android.example.aplicaciongestion;

public class Motocicletas {
    private String titulo;
    private String contenido;
    private int puntuacion;
    private String direccionWeb;
    private String telefono;
    private int imagenResId;  // ID del recurso de imagen
    private boolean radioSeleccionado;

    // Constructor
    public Motocicletas(String titulo, String contenido, int puntuacion, String direccionWeb, String telefono, int imagenResId, boolean radioSeleccionado) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.puntuacion = puntuacion;
        this.direccionWeb = direccionWeb;
        this.telefono = telefono;
        this.imagenResId = imagenResId;
        this.radioSeleccionado = radioSeleccionado;
    }

    // Getters y setters
    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public int getPuntuacion() { return puntuacion; }
    public String getDireccionWeb() { return direccionWeb; }
    public String getTelefono() { return telefono; }

    public int getImagenResId() { return imagenResId; }
    public boolean isRadioSeleccionado() { return radioSeleccionado; }
    public void setRadioSeleccionado(boolean radioSeleccionado) { this.radioSeleccionado = radioSeleccionado; }
}
