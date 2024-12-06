package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarMotocicleta extends AppCompatActivity {

    private EditText edtTitulo, edtContenido, edtPrecio;
    private RatingBar ratingBar;
    private ImageView imagenSeleccionada;
    private Button btnGuardar;
    private int imagenResId = R.drawable.moto1; // Imagen por defecto
    MainMotocicletas mainMotocicletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_moto);

        // Inicializar campos de texto y botones
        edtTitulo = findViewById(R.id.edtTitulo);
        edtContenido = findViewById(R.id.edtContenido);
        edtPrecio = findViewById(R.id.edtPrecio);
        ratingBar = findViewById(R.id.ratingBar);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);
        btnGuardar = findViewById(R.id.btnGuardar);


        // Configurar el botón para guardar la nueva motocicleta
        btnGuardar.setOnClickListener(v -> {
///On click
                // Obtener los valores de los campos
                String titulo = edtTitulo.getText().toString();
                String contenido = edtContenido.getText().toString();
                int puntuacion = (int) ratingBar.getRating();
                double precio = Double.parseDouble(edtPrecio.getText().toString());
                int imagen =(imagenSeleccionada.getImageAlpha());


                // Crear el objeto Motocicletas
                Motocicletas nuevaMoto = new Motocicletas(titulo, imagen, precio, puntuacion, contenido);

                // Volver a la actividad principal y pasar la nueva moto
                Intent resultado = new Intent();
                resultado.putExtra("nuevaMotocicleta", nuevaMoto); // Asegúrate de que Motocicletas implementa Parcelable
                setResult(RESULT_OK, resultado);
                finish();



            });
    }



    }


