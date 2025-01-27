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
        setContentView(R.layout.informacion_activity); // Asegúrate de crear este layout

        textViewInformacion = findViewById(R.id.textViewInformacion);
        cargarInformacionDesdeTxt();
    }

    private void cargarInformacionDesdeTxt() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.info); // Asegúrate de que tu archivo se llama 'informacion.txt' y está en la carpeta raw
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textViewInformacion.setText(stringBuilder.toString());
    }
}
