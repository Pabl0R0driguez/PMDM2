package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Encontrar el Spinner en el layout
        Spinner spinner = findViewById(R.id.desplegable);

        // Definir lista de opciones para el Spinner
        String[] lista = {"Opción 1", "Opción 2", "Opción 3"};

        // Crear un ArrayAdapter usando el layout predeterminado de Android para los elementos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                lista
        );

        // Especificar el layout para las opciones desplegables del Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Asociar el adaptador al Spinner
        spinner.setAdapter(adapter);

        // Listener para manejar la selección del usuario
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                // Obtener el elemento seleccionado
                String seleccionado = parent.getItemAtPosition(i).toString();
                // Mostrar mensaje Toast al seleccionar una opción
                Toast.makeText(MainActivity2.this, "Seleccionaste " + seleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Opcional: Manejo cuando no se selecciona ningún elemento
            }
        });
    }
}
