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
        setSupportActionBar(toolbar);

        // Inicializar ListView
        listaMotos = findViewById(R.id.listaMotocicletas);

        // Crear y cargar la lista de motocicletas
        listaMotocicletas = new ArrayList<>();
        listaMotocicletas.add(new Motocicletas("Yamaha MT-07", 2, 3,  R.drawable.moto1,"Yamaha"));
        listaMotocicletas.add(new Motocicletas("Kawasaki Ninja ZX-10R", 5, 5,  R.drawable.moto2, "34566"));

        // Configurar el adaptador para el ListView
        adaptador = new MotocicletasAdapter(this, listaMotocicletas);
        listaMotos.setAdapter(adaptador);

        // Configurar el botón flotante para agregar una nueva motocicleta
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainMotocicletas.this, AgregarMotocicleta.class);
            startActivityForResult(intent, 1); // 1 es el requestCode
        });

        // Configurar el long-click para eliminar elementos
        listaMotos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la motocicleta seleccionada
                Motocicletas motoEliminada = listaMotocicletas.get(position);

                // Eliminarla de la lista
                listaMotocicletas.remove(position);

                // Notificar al adaptador que la lista ha cambiado
                adaptador.notifyDataSetChanged();

                // Mostrar un Toast confirmando la eliminación
                Toast.makeText(MainMotocicletas.this, "Motocicleta eliminada: " + motoEliminada.getTitulo(), Toast.LENGTH_SHORT).show();

                return true; // Indica que el evento fue manejado
            }
        });
    }


    public MotocicletasAdapter getAdaptador(){
        return adaptador;
    }

    public List<Motocicletas> getListaMotocicletas(){
        return  listaMotocicletas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tools, menu);  // Inflar el menú normal
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filtrar) {
            Collections.sort(listaMotocicletas, new Comparator<Motocicletas>() {
                @Override
                public int compare(Motocicletas moto1, Motocicletas moto2) {
                    return Double.compare(moto2.getPrecio(), moto1.getPrecio()); // Orden descendente por precio
                }
            });
            adaptador.notifyDataSetChanged();  // Actualizar la vista
            return true;
        }
        if (item.getItemId() == R.id.filtrar_menos) {
            Collections.sort(listaMotocicletas, new Comparator<Motocicletas>() {
                @Override
                public int compare(Motocicletas moto1, Motocicletas moto2) {
                    return Double.compare(moto1.getPrecio(), moto2.getPrecio()); // Orden ascendente por precio
                }
            });
            adaptador.notifyDataSetChanged();  // Actualizar la vista
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
