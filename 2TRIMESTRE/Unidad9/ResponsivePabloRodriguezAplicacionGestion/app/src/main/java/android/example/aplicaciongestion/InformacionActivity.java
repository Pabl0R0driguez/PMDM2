package android.example.aplicaciongestion;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InformacionActivity extends AppCompatActivity {

    private TextView textViewInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_activity); // Cargar el layout

        textViewInformacion = findViewById(R.id.textViewInformacion); // Referencia al TextView

        // Cargar la información que desees mostrar
        cargarInformacion();
    }

    private void cargarInformacion() {
        String informacion = obtenerInformacionDesdeArchivo(); // Método para obtener la información del archivo
        textViewInformacion.setText(informacion); // Establecer la información en el TextView
    }

    private String obtenerInformacionDesdeArchivo() {
        StringBuilder contenido = new StringBuilder();
        InputStream inputStream = getResources().openRawResource(R.raw.info); // Asegúrate de que el archivo existe
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            contenido.append("Error al cargar la información.");
        }

        return contenido.toString();
    }

}
