package android.example.aplicaciongestion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MotocicletasAdapter extends ArrayAdapter<Motocicletas> {

    private List<Motocicletas> motocicletas; // Usamos List<Motocicletas> en lugar de un array

    public MotocicletasAdapter(Context context, List<Motocicletas> motocicletas) {
        // Llamamos al contexto, al layout de los elementos y a la lista de motocicletas
        super(context, R.layout.motocicletas_elementos, motocicletas);
        this.motocicletas = motocicletas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.motocicletas_elementos, parent, false);
        }

        // Obtener el elemento actual usando List
        Motocicletas elemento = motocicletas.get(position);

        // Inicializar y asignar las vistas
        ImageView imagen = view.findViewById(R.id.imagenElemento);
        TextView titulo = view.findViewById(R.id.tituloElemento);
        TextView contenido = view.findViewById(R.id.contenidoElemento);
        RatingBar ratingBar = view.findViewById(R.id.ratingElemento);
        TextView precio = view.findViewById(R.id.precioElemento);

        // Asignar valores a las vistas desde el elemento actual
        titulo.setText(elemento.getTitulo());
        contenido.setText(elemento.getContenido());
        ratingBar.setRating(elemento.getPuntuacion());

// Convertir el precio de double a String con 2 decimales y asignarlo al TextView
        precio.setText(String.format("%.2f", elemento.getPrecio()));

        imagen.setImageResource(elemento.getImagenResId());





        return view;
    }
}
