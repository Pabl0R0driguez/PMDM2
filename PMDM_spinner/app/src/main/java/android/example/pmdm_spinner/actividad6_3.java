package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class actividad6_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_6_3);

        TextView texto = findViewById(R.id.textView);
        Spinner listado = (Spinner) findViewById(R.id.spinner);

        final String[] datos = new String[]{"Elemento 1", "Elemento 2", "Elemento 3" , "Elemento 4"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        listado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String elementoSeleccionado = datos[position];
                // Actualizar el TextView con el elemento seleccionado
                texto.setText(elementoSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Aquí puedes manejar el caso cuando no hay nada seleccionado
                texto.setText("Ningún elemento seleccionado");
            }
        });


    }
    }


