package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Definimos los elementos del xml para poder utilizarlos ahora
        Button boton = findViewById(R.id.boton);
        TextView textoSeleccionado = findViewById(R.id.texto);
        Spinner spinner = findViewById(R.id.desplegable2);

        //SPINNER
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
                Toast.makeText(MainActivity3.this, "Seleccionaste " + seleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Opcional: Manejo cuando no se selecciona ningún elemento
            }
        });
        //

        //Cuando pulsemos el botón cambiaremos el textView por la opciónn seleccionada
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seleccionado = spinner.getSelectedItem().toString();
                textoSeleccionado.setText(seleccionado);
            }
        });

    }



}
