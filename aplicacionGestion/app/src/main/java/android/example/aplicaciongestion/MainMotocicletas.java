package android.example.aplicaciongestion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

        // Inflar el layout "motocicletas_elementos" donde está el botón DatePicker
        View view = getLayoutInflater().inflate(R.layout.motocicletas_elementos, null);
        btnDatePicker = view.findViewById(R.id.botonFecha);  // Asegúrate de que este ID coincida con el del XML
        // Enlazar el TextView donde se mostrará la fecha
        textViewFecha = view.findViewById(R.id.textViewFecha);
        // Configurar el listener del botón para el DatePicker
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha actual
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Crear y mostrar el DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainMotocicletas.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                // Mostrar la fecha seleccionada
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                textViewFecha.setText("Fecha seleccionada: " + selectedDate); // Mostrar en el TextView
                            }
                        },
                        year, month, dayOfMonth
                );

                // Mostrar el DatePicker
                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú desde el XML
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.eliminar) {
            // Acción para eliminar el elemento seleccionado
            Toast.makeText(this, "Eliminar opción seleccionada", Toast.LENGTH_SHORT).show();
            return true;

        } else if (item.getItemId() == R.id.modificar) {
            // Acción para modificar el elemento seleccionado
            Toast.makeText(this, "Modificar opción seleccionada", Toast.LENGTH_SHORT).show();
            return true;

        } else if (item.getItemId() == R.id.filtrar) {
            // Acción para ordenar por precio mayor (orden descendente)
            Collections.sort(listaMotocicletas, new Comparator<Motocicletas>() {
                @Override
                public int compare(Motocicletas moto1, Motocicletas moto2) {
                    return Double.compare(moto2.getPrecio(), moto1.getPrecio());  // Orden descendente por precio
                }
            });
            adaptador.notifyDataSetChanged();  // Actualizar la vista
            return true;

        } else if (item.getItemId() == R.id.filtrar_menos) {
            // Acción para ordenar por precio menor (orden ascendente)
            Collections.sort(listaMotocicletas, new Comparator<Motocicletas>() {
                @Override
                public int compare(Motocicletas moto1, Motocicletas moto2) {
                    return Double.compare(moto1.getPrecio(), moto2.getPrecio());  // Orden ascendente por precio
                }
            });
            adaptador.notifyDataSetChanged();  // Actualizar la vista
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;

        if (item.getItemId() == R.id.eliminar) {
            // Eliminar la motocicleta de la lista
            listaMotocicletas.remove(position);
            adaptador.notifyDataSetChanged();  // Actualizar la vista
            Toast.makeText(this, "Motocicleta eliminada", Toast.LENGTH_SHORT).show();
            return true;

        } else {
            return super.onContextItemSelected(item);
        }
    }
}
