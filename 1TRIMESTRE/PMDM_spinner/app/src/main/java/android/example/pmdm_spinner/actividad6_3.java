package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class actividad6_3 extends AppCompatActivity {

    private ArrayList<String> datos;
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_6_3);

        Button boton1 = findViewById(R.id.btmas);
        Button boton2 = findViewById(R.id.bteliminar);
        TextView texto = findViewById(R.id.textView);
        Spinner listado = (Spinner) findViewById(R.id.spinner);

        datos = new ArrayList<>();
        datos.add("Elemento 1");
        datos.add("Elemento 2");
        datos.add("Elemento 3");

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        listado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String elementoSeleccionado = datos.get(position);
                // Actualizar el TextView con el elemento seleccionado
                texto.setText(elementoSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Aquí puedes manejar el caso cuando no hay nada seleccionado
                texto.setText("Ningún elemento seleccionado");
            }
        });

        // Evento para el botón de añadir
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Añadir un nuevo elemento a la lista
                datos.add("Elemento " + (datos.size() + 1));
                adaptador.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.remove(datos.size() - 1);
                adaptador.notifyDataSetChanged();
            }

        });
    }
}


