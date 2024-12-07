package android.example.aplicaciongestion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MotocicletasAdapter extends ArrayAdapter<Motocicletas> {

    private Context context;
    private List<Motocicletas> motocicletasOriginales;  // Lista original
    private List<Motocicletas> motocicletasFiltradas;  // Lista filtrada
    private List<Motocicletas> motocicletas;  // Lista

    public MotocicletasAdapter(Context context, List<Motocicletas> motocicletas) {
        super(context, R.layout.motocicletas_elementos, motocicletas);
        this.context = context;
        this.motocicletasOriginales = new ArrayList<>(motocicletas);  // Copiar la lista original
        this.motocicletasFiltradas = new ArrayList<>(motocicletas);  // Inicializar la lista filtrada
        this.motocicletas = new ArrayList<>(motocicletas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar la vista si es nula
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.motocicletas_elementos, parent, false);
        }

        // Obtener la motocicleta actual desde la lista filtrada
        Motocicletas motocicleta = motocicletasFiltradas.get(position);

        // Vincular las vistas
        ImageView imagen = view.findViewById(R.id.imagenElemento);
        TextView titulo = view.findViewById(R.id.tituloElemento);
        TextView contenido = view.findViewById(R.id.contenidoElemento);
        RatingBar ratingBar = view.findViewById(R.id.ratingElemento);
        TextView precio = view.findViewById(R.id.precioElemento);
        TextView textViewFecha = view.findViewById(R.id.textViewFecha);
        Button btnDatePicker = view.findViewById(R.id.botonFecha);

        // Configurar las vistas con los datos de la motocicleta
        imagen.setImageResource(motocicleta.getImagenResId());
        titulo.setText(motocicleta.getTitulo());
        contenido.setText(motocicleta.getContenido());
        ratingBar.setRating(motocicleta.getPuntuacion());
        precio.setText(String.format("%.2f €", motocicleta.getPrecio()));

        // Configuración del botón para seleccionar fecha
        btnDatePicker.setOnClickListener(v -> {
            // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Mostrar el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    context,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String fechaSeleccionada = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        textViewFecha.setText("Fecha: " + fechaSeleccionada);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });



        // Registrar el menú contextual para el elemento actual
        view.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            MenuInflater inflater = ((MainMotocicletas) context).getMenuInflater();
            inflater.inflate(R.menu.menu_contextual, menu); // Inflar el menú contextual desde el XML

            menu.findItem(R.id.eliminar).setOnMenuItemClickListener(item -> {
                // Eliminar la motocicleta actual
                motocicletasFiltradas.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Motocicleta eliminada", Toast.LENGTH_SHORT).show();
                return true;
            });

        });



        return view;
    }


    // Método para eliminar un ítem
    public void removeItem(int position) {
        Motocicletas motocicletaAEliminar = motocicletasFiltradas.get(position); // Obtener el ítem a eliminar

        // Eliminar de la lista filtrada
        motocicletasFiltradas.remove(position);

        // Eliminar también de la lista original (para que se mantenga sincronizada)
        motocicletas.remove(motocicletaAEliminar);

        notifyDataSetChanged(); // Notificar al adaptador que la vista ha cambiado
    }



    // Método para filtrar las motocicletas por nombre
    public void filtrar(String query) {
        if (query.isEmpty()) {
            // Si el query está vacío, mostrar todas las motocicletas
            motocicletasFiltradas = new ArrayList<>(motocicletasOriginales);
        } else {
            List<Motocicletas> listaFiltrada = new ArrayList<>();
            for (Motocicletas moto : motocicletasOriginales) {
                if (moto.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                    listaFiltrada.add(moto);
                }
            }
            motocicletasFiltradas = listaFiltrada;  // Asignar la lista filtrada
        }

        // Actualizar la lista del adaptador y notificar el cambio
        clear(); // Limpiar los datos actuales
        addAll(motocicletasFiltradas); // Añadir los datos filtrados
        notifyDataSetChanged();  // Notificar al adaptador
    }


    // Método para ordenar las motocicletas por precio de mayor a menor
    public void ordenarPorPrecioMayorAMenor() {
        motocicletasFiltradas.sort((m1, m2) -> Double.compare(m2.getPrecio(), m1.getPrecio()));  // Ordenar de mayor a menor
        notifyDataSetChanged();  // Notificar al adaptador para actualizar la vista
    }

    // Método para ordenar las motocicletas por precio de menor a mayor
    public void ordenarPorPrecioMenorAMayor() {
        motocicletasFiltradas.sort((m1, m2) -> Double.compare(m1.getPrecio(), m2.getPrecio()));  // Ordenar de menor a mayor
        notifyDataSetChanged();  // Notificar al adaptador para actualizar la vista
    }



}
