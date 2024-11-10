package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class actividad6_1 extends AppCompatActivity {

    // Creamos una lista de tipo ArrayList para manejar los datos dinámicamente
    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_6_1);

        Button boton1 = findViewById(R.id.bt9);
        Button boton2 = findViewById(R.id.bt10);
        TextView texto = findViewById(R.id.textView);
        ListView listado = findViewById(R.id.listView);

        // Inicializamos el ArrayList con los elementos iniciales
        datos = new ArrayList<>();
        datos.add("Elemento 1");
        datos.add("Elemento 2");
        datos.add("Elemento 3");

        // Configuramos el adaptador para el ListView
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        // Evento de clic en los elementos de la lista
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtener el texto del elemento seleccionado
                String elementoSeleccionado = datos.get(i);
                texto.setText(elementoSeleccionado); // Actualizar el TextView
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
