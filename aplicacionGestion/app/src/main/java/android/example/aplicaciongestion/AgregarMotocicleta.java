package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarMotocicleta extends AppCompatActivity {

    private EditText editTitulo, editContenido, editPrecio;
    private RatingBar ratingBar;
    private ImageView imagenMoto;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_moto);

        // Vincular vistas
        editTitulo = findViewById(R.id.edtTitulo);
        editContenido = findViewById(R.id.edtContenido);
        editPrecio = findViewById(R.id.edtPrecio);
        ratingBar = findViewById(R.id.ratingBar);
        imagenMoto = findViewById(R.id.imagenSeleccionada);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configurar el botón Guardar
        btnGuardar.setOnClickListener(v -> {
            // Obtener los datos de la nueva motocicleta
            String titulo = editTitulo.getText().toString();
            String contenido = editContenido.getText().toString();
            String precioStr = editPrecio.getText().toString();

            // Validar que el campo de precio no esté vacío
            if (precioStr.isEmpty()) {
                Toast.makeText(AgregarMotocicleta.this, "El precio es obligatorio", Toast.LENGTH_SHORT).show();
                return;  // Salir si el precio es inválido
            }
            float precio = Float.parseFloat(precioStr);
            float puntuacion = ratingBar.getRating();

            // Establecer una imagen por defecto (puedes hacer esto dinámico)
            int imagenResId = R.drawable.moto3;

            // Crear una nueva motocicleta
            Motocicletas nuevaMoto = new Motocicletas(titulo, (int) puntuacion, precio, imagenResId, contenido);

            // Devolver la motocicleta a la actividad principal
            Intent intent = new Intent();
            intent.putExtra("nuevaMoto", nuevaMoto);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
