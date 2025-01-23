package android.example.aplicaciongestion;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ModificarMotocicleta extends AppCompatActivity {

    private EditText edtTitulo, edtDescripcion, edtPrecio;
    private ImageView imgMotocicleta;
    private TextView txtFecha;
    private Button btnFecha;
    private RatingBar ratingBar; // Declarar RatingBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_moto);

        // Inicializar los campos de la UI
        edtTitulo = findViewById(R.id.edtTitulo);
        edtDescripcion = findViewById(R.id.edtContenido);
        edtPrecio = findViewById(R.id.edtPrecio);
        imgMotocicleta = findViewById(R.id.imagenSeleccionada);
        txtFecha = findViewById(R.id.textViewFecha);
        btnFecha = findViewById(R.id.botonFecha);
        ratingBar = findViewById(R.id.ratingBar); // Inicializar RatingBar


        // Obtener la motocicleta enviada desde la actividad anterior
        Intent intent = getIntent();
        Motocicletas moto = (Motocicletas) intent.getSerializableExtra("motocicleta");

        // Rellenar los campos con los datos actuales de la motocicleta
        edtTitulo.setText(moto.getTitulo());
        edtDescripcion.setText(moto.getContenido());
        edtPrecio.setText(String.valueOf(moto.getPrecio()));
        imgMotocicleta.setImageResource(moto.getImagenResId());

        // Si la motocicleta tiene una fecha guardada, mostrarla
        txtFecha.setText(moto.getFecha());

        // Mostrar la puntuación en el RatingBar
        ratingBar.setRating(moto.getPuntuacion()); // Asumiendo que `getRating` retorna un valor flotante

        // Configurar el botón para guardar los cambios
        ImageButton btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            // Crear una nueva motocicleta con los datos modificados
            moto.setTitulo(edtTitulo.getText().toString());
            moto.setContenido(edtDescripcion.getText().toString());
            moto.setPrecio(Double.parseDouble(edtPrecio.getText().toString()));
            moto.setFecha(txtFecha.getText().toString());

            // Obtener el valor del RatingBar
            moto.setPuntuacion((int) ratingBar.getRating()); // Guardar la puntuación seleccionada

            // Pasar la motocicleta modificada y la posición de vuelta
            Intent resultIntent = new Intent();
            resultIntent.putExtra("motocicletaModificada", moto);
            resultIntent.putExtra("position", getIntent().getIntExtra("position", -1));
            setResult(RESULT_OK, resultIntent);
            finish(); // Cerrar la actividad
        });

        // Botón cancelar
        ImageButton botonCancelar = findViewById(R.id.btnCancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModificarMotocicleta.this, MainMotocicletas.class);
                startActivity(intent); // Inicia la nueva actividad
                finish(); // Cierra la actividad actual
            }
        });



        // Configurar el botón para seleccionar fecha
        btnFecha.setOnClickListener(v -> {
            // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear y mostrar el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ModificarMotocicleta.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Mostrar la fecha seleccionada en el TextView
                        String fechaSeleccionada = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        txtFecha.setText(fechaSeleccionada);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }
}
