package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainMotocicletas extends AppCompatActivity {

    private ListView listaMotos;
    private MotocicletasAdapter adaptador;
    private List<Motocicletas> listaMotocicletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motocicletas_main);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // Establece el toolbar como la ActionBar

        // Inicializar ListView
        listaMotos = findViewById(R.id.listaMotocicletas);

        // Crear y cargar la lista de motocicletas
        listaMotocicletas = new ArrayList<>();
        listaMotocicletas.add(new Motocicletas("Yamaha MT-07", "Moto de alta gama", 3, "https://honda.com/image.jpg", 2223, R.drawable.moto1, false));
        listaMotocicletas.add(new Motocicletas("Kawasaki Ninja ZX-10R", "Moto deportiva", 5, "https://kawasaki.com/ninja-zx10r.jpg", 34566, R.drawable.moto2, false));
        listaMotocicletas.add(new Motocicletas("Honda CBR 600RR", "Moto deportiva de alto rendimiento", 4, "https://honda.com/cbr600rr.jpg", 9000, R.drawable.moto3, false));
        listaMotocicletas.add(new Motocicletas("Ducati Panigale V4", "Moto de alta gama", 5, "https://ducati.com/panigale-v4.jpg", 6777, R.drawable.moto4, false));

        // Configurar el adaptador para el ListView
        adaptador = new MotocicletasAdapter(this, listaMotocicletas);
        listaMotos.setAdapter(adaptador);

        // Configurar el botón flotante para agregar una nueva motocicleta
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainMotocicletas.this, AgregarMotocicleta.class);
            startActivityForResult(intent, 1); // 1 es el requestCode
        });

        // Configurar el botón flotante de borrar
        FloatingActionButton fabDelete = findViewById(R.id.fab_delete);
        fabDelete.setOnClickListener(v -> {
            boolean elementoEliminado = false;

            // Buscar el elemento con radioSeleccionado = true
            for (int i = 0; i < listaMotocicletas.size(); i++) {
                if (listaMotocicletas.get(i).isRadioSeleccionado()) {
                    listaMotocicletas.remove(i); // Eliminar el elemento
                    elementoEliminado = true;
                    adaptador.notifyDataSetChanged(); // Actualizar la vista
                    break; // Salir del bucle una vez encontrado
                }
            }

            // Mostrar mensaje al usuario
            if (elementoEliminado) {
                Toast.makeText(MainMotocicletas.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainMotocicletas.this, "Ningún elemento seleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú desde el archivo XML
        getMenuInflater().inflate(R.menu.menu_tools, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verificar el ID del item seleccionado
        if (item.getItemId() == R.id.filtrar) {
            // Ordenar las motocicletas por precio de mayor a menor
            Collections.sort(listaMotocicletas, new Comparator<Motocicletas>() {
                @Override
                public int compare(Motocicletas moto1, Motocicletas moto2) {
                    return Double.compare(moto2.getPrecio(), moto1.getPrecio()); // Orden descendente por precio
                }
            });

            // Notificar al adaptador para que actualice la vista
            adaptador.notifyDataSetChanged();
            return true;
        }

        // Si no es el ítem que buscamos, delegamos el manejo al padre
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            // Recuperar la motocicleta
            Motocicletas nuevaMoto = (Motocicletas) data.getSerializableExtra("nuevaMotocicleta");

            if (nuevaMoto != null) {
                listaMotocicletas.add(nuevaMoto); // Añadir la nueva motocicleta a la lista
                adaptador.notifyDataSetChanged(); // Notificar al adaptador para actualizar la vista
            }
        }
    }
}
