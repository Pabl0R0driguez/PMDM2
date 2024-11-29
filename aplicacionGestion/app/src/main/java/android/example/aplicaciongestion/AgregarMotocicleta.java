package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarMotocicleta extends AppCompatActivity {

    private EditText edtTitulo, edtContenido, edtDireccionWeb, edtTelefono;
    private RatingBar ratingBar;
    private ImageView imagenSeleccionada;
    private Button btnSeleccionarImagen, btnGuardar;
    private int imagenResId = R.drawable.moto1; // Imagen por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_moto);

        // Inicializar campos de texto y botones
        edtTitulo = findViewById(R.id.edtTitulo);
        edtContenido = findViewById(R.id.edtContenido);
        edtDireccionWeb = findViewById(R.id.edtDireccionWeb);
        edtTelefono = findViewById(R.id.edtTelefono);
        ratingBar = findViewById(R.id.ratingBar);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configurar el botón para seleccionar imagen (por ahora solo usar imagen predeterminada)
        btnSeleccionarImagen.setOnClickListener(v -> {
            // Aquí se puede agregar el código para seleccionar imagen desde galería o cámara
            // Actualmente se mantiene la imagen predeterminada
            imagenSeleccionada.setImageResource(imagenResId);
        });

        btnGuardar.setOnClickListener(v -> {
            String titulo = edtTitulo.getText().toString();
            String contenido = edtContenido.getText().toString();
            String direccionWeb = edtDireccionWeb.getText().toString();
            double telefono = edtTelefono.getAlpha();
            int puntuacion = (int) ratingBar.getRating();

            // Crear el objeto Motocicletas y añadirlo a la lista
            Motocicletas nuevaMoto = new Motocicletas(titulo, contenido, puntuacion, direccionWeb, telefono, imagenResId);

            // Volver a la actividad principal y pasar la nueva moto
            Intent resultado = new Intent();
            resultado.putExtra("nuevaMotocicleta", nuevaMoto); // Pasar el objeto correctamente
            setResult(RESULT_OK, resultado);
            finish();
        });

    }
}
