package android.example.aplicaciongestion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AgregarMotocicleta extends AppCompatActivity {

    private EditText edtTitulo, edtContenido, edtPrecio;
    private RatingBar ratingBar;
    private Button  btnSeleccionarFecha;
    private ImageButton btnGuardar;
    private TextView textViewFecha;
    private String fechaSeleccionada = "Fecha no seleccionada"; // Valor inicial de la fecha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_moto);

        // Inicializar las vistas
        edtTitulo = findViewById(R.id.edtTitulo);
        edtContenido = findViewById(R.id.edtContenido);
        edtPrecio = findViewById(R.id.edtPrecio);
        ratingBar = findViewById(R.id.ratingBar);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSeleccionarFecha = findViewById(R.id.botonFecha);
        textViewFecha = findViewById(R.id.textViewFecha);

        // Configurar el OnClickListener para el botón "Seleccionar Fecha"
        btnSeleccionarFecha.setOnClickListener(v -> seleccionarFecha());

        // Configurar el OnClickListener para el botón "Guardar Moto"
        btnGuardar.setOnClickListener(v -> {
            String titulo = edtTitulo.getText().toString();
            String contenido = edtContenido.getText().toString();
            String precioStr = edtPrecio.getText().toString();
            float rating = ratingBar.getRating();

            // Validación de los campos
            if (titulo.isEmpty() || contenido.isEmpty() || precioStr.isEmpty() || fechaSeleccionada.equals("Fecha no seleccionada")) {
                // Inflar el layout personalizado del Toast
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_personalizado,
                        findViewById(R.id.customToastContainer)); // El contenedor donde estará el Toast

                // Obtener el TextView del layout inflado y establecer el mensaje
                TextView toastText = layout.findViewById(R.id.toastText);
                toastText.setText("Por favor complete todos los campos");

                // Configurar el Toast y mostrarlo
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout); // Establecer el layout personalizado
                toast.show();

                return; // Terminar la ejecución si falta algún campo
            }

            double precio = Double.parseDouble(precioStr);

            // Crear una nueva motocicleta con los datos ingresados
            Motocicletas nuevaMoto = new Motocicletas(titulo, (int) rating, precio, R.drawable.moto1, contenido, fechaSeleccionada);

            // Crear un Intent para enviar la motocicleta a la actividad principal
            Intent resultIntent = new Intent();
            resultIntent.putExtra("nuevaMoto", nuevaMoto); // Enviar la nueva moto como un extra
            setResult(RESULT_OK, resultIntent);

            // Notificar al usuario que la moto fue guardada
            Toast.makeText(AgregarMotocicleta.this, "Motocicleta guardada", Toast.LENGTH_SHORT).show();

            // Finalizar la actividad
            finish();
        });

        // Botón cancelar
        ImageButton botonCancelar = findViewById(R.id.btnCancelar);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarMotocicleta.this, MainMotocicletas.class);
                startActivity(intent); // Inicia la nueva actividad
                finish(); // Cierra la actividad actual
            }
        });
    }



    // Método para mostrar el selector de fecha
    private void seleccionarFecha() {
        // Obtener la fecha actual
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        // Crear el selector de fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    // Formatear la fecha seleccionada
                    fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    textViewFecha.setText(fechaSeleccionada); // Mostrar la fecha seleccionada en el TextView
                }, anio, mes, dia);

        // Mostrar el selector de fecha
        datePickerDialog.show();

    }
}
