package android.example.aplicaciongestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import android.app.DatePickerDialog;

import java.util.List;
import java.util.Calendar;

public class MotocicletasAdapter extends ArrayAdapter<Motocicletas> {

    private List<Motocicletas> motocicletas; // Lista de motocicletas
    private Context context;

    public MotocicletasAdapter(Context context, List<Motocicletas> motocicletas) {
        super(context, R.layout.motocicletas_elementos, motocicletas);
        this.context = context;
        this.motocicletas = motocicletas;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.motocicletas_elementos, parent, false);
        }

        // Obtener el objeto de la motocicleta en la posición actual
        final Motocicletas motocicleta = motocicletas.get(position);

        // Inicializar las vistas
        ImageView imagen = view.findViewById(R.id.imagenElemento);
        TextView titulo = view.findViewById(R.id.tituloElemento);
        TextView contenido = view.findViewById(R.id.contenidoElemento);
        RatingBar ratingBar = view.findViewById(R.id.ratingElemento);
        TextView precio = view.findViewById(R.id.precioElemento);
        final TextView textViewFecha = view.findViewById(R.id.textViewFecha);  // TextView para la fecha
        Button btnDatePicker = view.findViewById(R.id.botonFecha);  // Botón para seleccionar la fecha

        // Asignar los valores a las vistas
        titulo.setText(motocicleta.getTitulo());
        contenido.setText(motocicleta.getContenido());
        ratingBar.setRating(motocicleta.getPuntuacion());
        precio.setText(String.format("%.2f", motocicleta.getPrecio()));
        imagen.setImageResource(motocicleta.getImagenResId());

        // Configuración del botón para seleccionar la fecha
        btnDatePicker.setOnClickListener(v -> {
            // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Crear y mostrar el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    context,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        // Mostrar la fecha seleccionada en el TextView correspondiente
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        textViewFecha.setText(selectedDate);  // Actualizamos el TextView con la fecha seleccionada
                    },
                    year, month, dayOfMonth
            );

            // Mostrar el DatePicker
            datePickerDialog.show();
        });

        return view;
    }
}