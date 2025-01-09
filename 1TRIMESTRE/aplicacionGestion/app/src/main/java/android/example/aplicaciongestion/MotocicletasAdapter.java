package android.example.aplicaciongestion;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MotocicletasAdapter extends ArrayAdapter<Motocicletas> {

    private Context context;
    private List<Motocicletas> motocicletasOriginales;  // Lista original
    private List<Motocicletas> motocicletasFiltradas;  // Lista filtrada
    private List<Motocicletas> motocicletas;  // Lista principal

    public MotocicletasAdapter(Context context, List<Motocicletas> motocicletas) {
        super(context, R.layout.motocicletas_elementos, motocicletas);
        this.context = context;
        this.motocicletasOriginales = new ArrayList<>(motocicletas);  // Copiar la lista original
        this.motocicletasFiltradas = new ArrayList<>(motocicletas);  // Inicializar la lista filtrada
        this.motocicletas = new ArrayList<>(motocicletas);  // Lista principal
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

        // Configurar las vistas con los datos de la motocicleta
        imagen.setImageResource(motocicleta.getImagenResId());
        titulo.setText(motocicleta.getTitulo());
        contenido.setText(motocicleta.getContenido());
        ratingBar.setRating(motocicleta.getPuntuacion());
        precio.setText(String.format("%.2f €", motocicleta.getPrecio()));
        textViewFecha.setText("Fecha: " + motocicleta.getFecha());  // Asumimos que la motocicleta tiene una fecha

        // Registrar el menú contextual para el elemento actual
        view.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            MenuInflater inflaterMenu = ((MainMotocicletas) context).getMenuInflater();
            inflaterMenu.inflate(R.menu.menu_contextual, menu); // Inflar el menú contextual desde el XML

            menu.findItem(R.id.eliminar).setOnMenuItemClickListener(item -> {
                // Eliminar la motocicleta actual
                removeItem(position);  // Usar el método `removeItem` de la clase para actualizar ambas listas
                Toast.makeText(context, "Motocicleta eliminada", Toast.LENGTH_SHORT).show();
                return true;
            });

            // Modificar la motocicleta
            menu.findItem(R.id.modificar).setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(context, ModificarMotocicleta.class);
                intent.putExtra("motocicleta", motocicleta); // Pasar la motocicleta a la actividad de modificación
                intent.putExtra("position", position); // Pasar la posición para actualizarla después
                ((MainMotocicletas) context).startActivityForResult(intent, MainMotocicletas.REQUEST_CODE_MODIFICAR_MOTO);
                return true;
            });
        });

        return view;
    }

    // Método para añadir una motocicleta
    public void addItem(Motocicletas nuevaMoto) {
        motocicletas.add(nuevaMoto);  // Añadir a la lista principal
        motocicletasOriginales.add(nuevaMoto); // Añadir a la lista original para futuras búsquedas
        motocicletasFiltradas.add(nuevaMoto); // Añadir a la lista filtrada
        notifyDataSetChanged(); // Refrescamos la vista
    }

    // Método para eliminar una motocicleta por posición
    public void removeItem(int position) {
        if (position >= 0 && position < motocicletasFiltradas.size()) {
            Motocicletas motocicletaAEliminar = motocicletasFiltradas.get(position);

            motocicletas.remove(motocicletaAEliminar); // Eliminar de la lista principal
            motocicletasFiltradas.remove(motocicletaAEliminar); // Eliminar de la lista filtrada
            motocicletasOriginales.remove(motocicletaAEliminar); // Asegurarnos de que también se elimina de la lista original

            notifyDataSetChanged(); // Refrescar la vista
        } else {
            Log.e("MotocicletasAdapter", "Índice inválido para eliminar: " + position);
        }
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

    @Override
    public int getCount() {
        return motocicletasFiltradas.size();
    }

    @Override
    public Motocicletas getItem(int position) {
        return motocicletasFiltradas.get(position);
    }

    // Método para actualizar una motocicleta en la lista
    public void updateItem(int position, Motocicletas motocicletaModificada) {
        if (position >= 0 && position < motocicletasFiltradas.size()) {
            motocicletasFiltradas.set(position, motocicletaModificada); // Actualizar en la lista filtrada
            motocicletas.set(position, motocicletaModificada); // Actualizar en la lista principal
            motocicletasOriginales.set(position, motocicletaModificada); // También actualizar en la lista original
            notifyDataSetChanged(); // Notificar al adaptador para refrescar la vista
        }
    }
}
