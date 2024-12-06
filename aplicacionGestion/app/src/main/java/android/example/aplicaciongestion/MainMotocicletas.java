package android.example.aplicaciongestion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainMotocicletas extends AppCompatActivity {

    private ListView listaMotos;
    private MotocicletasAdapter adaptador;
    private List<Motocicletas> listaMotocicletas;
    private Button btnDatePicker;
    private TextView textViewFecha;  // Añadir el TextView para la fecha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motocicletas_main); // Asegúrate de que este es el layout correcto para la actividad principal

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar el ListView
        listaMotos = findViewById(R.id.listaMotocicletas);
        registerForContextMenu(listaMotos);  // Registrar el menú contextual

        // Crear y cargar la lista de motocicletas
        listaMotocicletas = new ArrayList<>();
        listaMotocicletas.add(new Motocicletas("Ducati Panigale V4", 5, 5000, R.drawable.moto3,
                "Superbike de alto rendimiento con motor V4 de 1103 cc y 214 caballos de fuerza."));
        listaMotocicletas.add(new Motocicletas("BMW R 1250 GS", 4, 5500, R.drawable.moto7,
                "Motocicleta de aventura con motor bóxer de 1254 cc, ideal para viajes largos y todo terreno."));
        listaMotocicletas.add(new Motocicletas("Honda CBR650R", 3, 4000, R.drawable.moto5,
                "Moto deportiva de 649 cc, perfecta para quienes buscan agilidad y rendimiento en carretera."));
        listaMotocicletas.add(new Motocicletas("Suzuki Hayabusa", 5, 6500, R.drawable.moto1,
                "Motocicleta deportiva de 1340 cc, famosa por su velocidad y rendimiento en pista."));
        listaMotocicletas.add(new Motocicletas("KTM 390 Duke", 3, 3000, R.drawable.moto2,
                "Moto naked de 373 cc, ligera y ágil, ideal para principiantes y la ciudad."));

        // Configurar el adaptador para el ListView
        adaptador = new MotocicletasAdapter(this, listaMotocicletas);
        listaMotos.setAdapter(adaptador);

        // Configurar el botón flotante para agregar una nueva motocicleta
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainMotocicletas.this, AgregarMotocicleta.class);
            startActivityForResult(intent, 1); // 1 es el requestCode
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú desde el XML
        getMenuInflater().inflate(R.menu.menu, menu);

        // Configurar el SearchView para filtrar las motocicletas
        MenuItem searchItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Agregar un listener para el cambio de texto
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filtrar las motocicletas al presionar Enter
                filtrarMotocicletas(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar las motocicletas mientras se escribe
                filtrarMotocicletas(newText);
                return true;
            }
        });

        return true;
    }

    // Método para filtrar las motocicletas
    private void filtrarMotocicletas(String query) {
        adaptador.filtrar(query);  // Llamar al método filtrar en el adaptador

        if (adaptador.getCount() == 0) {
            Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }
}
