package android.example.aplicaciongestion;

public class Motocicletas {
    private String titulo;
    private String contenido;
    private String direccionWeb;
    private String telefono;
    private int imagenId;
    private boolean seleccionado;
    private float puntuacion;

    public Motocicletas(String titulo, String contenido, String direccionWeb, String telefono, int imagenId, boolean seleccionado, float puntuacion) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.direccionWeb = direccionWeb;
        this.telefono = telefono;
        this.imagenId = imagenId;
        this.seleccionado = seleccionado;
        this.puntuacion = puntuacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDireccionWeb() {
        return direccionWeb;
    }

    public void setDireccionWeb(String direccionWeb) {
        this.direccionWeb = direccionWeb;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }
}