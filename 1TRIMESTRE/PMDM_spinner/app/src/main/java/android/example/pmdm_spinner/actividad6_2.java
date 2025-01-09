package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class actividad6_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_6_2);

        TextView texto = findViewById(R.id.textView);
        GridView listado = (GridView) findViewById(R.id.gridView);

        final String[] datos = new String[]{"Elemento 1", "Elemento 2", "Elemento 3" , "Elemento 4"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Obtener el texto del elemento seleccionado
                String elementoSeleccionado = datos[i];
                texto.setText(elementoSeleccionado); // Actualizar el TextView
            }
        });
    }
}
