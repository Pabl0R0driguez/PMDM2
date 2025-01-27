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
    private TextView textTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_activity);

        textTitulo = findViewById(R.id.titulo);
        textViewInformacion = findViewById(R.id.textViewInformacion);

        cargarInformacionDesdeTxt();
        try {
            cargarTiutlo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarInformacionDesdeTxt() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.info);
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

        private void cargarTiutlo() throws IOException {
            StringBuilder stringBuilder1 = new StringBuilder();

            try {
                InputStream inputStream1 = getResources().openRawResource(R.raw.titulo);
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
                String line1;
                while ((line1 = reader1.readLine()) != null) {
                    stringBuilder1.append(line1).append("\n");
                }
                reader1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        textTitulo.setText(stringBuilder1.toString());
        }

    }

