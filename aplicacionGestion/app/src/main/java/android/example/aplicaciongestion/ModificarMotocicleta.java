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

public class ModificarMotocicleta extends AppCompatActivity {

    private EditText edtTitulo, edtDescripcion, edtPrecio;
    private ImageView imgMotocicleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_moto);

        // Inicializar los campos de la UI
        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescripcion = findViewById(R.id.edtContenido);
        imgMotocicleta = findViewById(R.id.imagenSeleccionada);

        // Obtener la motocicleta enviada desde la actividad anterior
        Intent intent = getIntent();
        Motocicletas moto = (Motocicletas) intent.getSerializableExtra("motocicleta");

        // Rellenar los campos con los datos actuales de la motocicleta
        edtTitulo.setText(moto.getTitulo());
        edtDescripcion.setText(moto.getContenido());
        edtPrecio.setText(String.valueOf(moto.getPrecio()));
        imgMotocicleta.setImageResource(moto.getImagenResId());

        // Configurar el botón para guardar los cambios
        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            // Crear una nueva motocicleta con los datos modificados
            moto.setTitulo(edtTitulo.getText().toString());
            moto.setContenido(edtDescripcion.getText().toString());
            moto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));

        });
    }
}
