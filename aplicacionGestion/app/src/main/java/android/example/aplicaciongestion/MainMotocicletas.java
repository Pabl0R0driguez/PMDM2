package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainMotocicletas extends AppCompatActivity {

    private ListView listaMotos; // ListView para mostrar las motocicletas
    private MotocicletasAdapter adaptador; // Adaptador personalizado
    private List<Motocicletas> listaMotocicletas; // Lista de motocicletas
    private static final int REQUEST_CODE_AGREGAR_MOTO = 1; // Código de solicitud para agregar moto
    static final int REQUEST_CODE_MODIFICAR_MOTO = 1; // Código de solicitud para agregar moto


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motocicletas_main); // Layout principal de la actividad

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar el ListView
        listaMotos = findViewById(R.id.listaMotocicletas);
        registerForContextMenu(listaMotos); // Registrar el menú contextual para el ListView

        // Crear y cargar la lista inicial de motocicletas
        listaMotocicletas = new ArrayList<>();
        cargarMotocicletas(); // Método para inicializar la lista de motocicletas

        // Configurar el adaptador para el ListView
        adaptador = new MotocicletasAdapter(this, listaMotocicletas);
        listaMotos.setAdapter(adaptador);

        // Configurar el botón flotante para agregar una nueva motocicleta
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainMotocicletas.this, AgregarMotocicleta.class);
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_MOTO);
        });
    }

    // Cargar motocicletas iniciales
    private void cargarMotocicletas() {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú desde el archivo XML
        getMenuInflater().inflate(R.menu.menu, menu);

        // Configurar el SearchView para filtrar motocicletas
        MenuItem searchItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarMotocicletas(query); // Filtrar cuando se presiona Enter
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarMotocicletas(newText); // Filtrar mientras se escribe
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar las opciones del menú
        if (item.getItemId() == R.id.filtrar) {
            adaptador.ordenarPorPrecioMayorAMenor();
            return true;
        } else if (item.getItemId() == R.id.filtrar_menos) {
            adaptador.ordenarPorPrecioMenorAMayor();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;

        if (item.getItemId() == R.id.eliminar) {
            adaptador.removeItem(position); // Eliminar la motocicleta desde el adaptador
            Toast.makeText(this, "Motocicleta eliminada", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    // Método para filtrar motocicletas
    private void filtrarMotocicletas(String query) {
        adaptador.filtrar(query);
        if (adaptador.getCount() == 0) {
            Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGREGAR_MOTO && resultCode == RESULT_OK && data != null) {
            Motocicletas nuevaMoto = (Motocicletas) data.getSerializableExtra("nuevaMoto");
            if (nuevaMoto != null) {
                adaptador.addItem(nuevaMoto);
                Toast.makeText(this, "Nueva motocicleta añadida", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al agregar motocicleta", Toast.LENGTH_SHORT).show();
            }
        }

        // Nueva parte para modificar la motocicleta
        if (requestCode == REQUEST_CODE_MODIFICAR_MOTO && resultCode == RESULT_OK && data != null) {
            Motocicletas motoModificada = (Motocicletas) data.getSerializableExtra("motocicletaModificada");
            if (motoModificada != null) {
                // Obtener la posición de la motocicleta modificada desde el adaptador
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    // Actualizar la motocicleta en la lista y notificar al adaptador
                    adaptador.updateItem(position, motoModificada);
                    Toast.makeText(this, "Motocicleta modificada", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error al modificar motocicleta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
