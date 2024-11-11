package android.example.aplicaciongestion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MotocicletasAdapter extends RecyclerView.Adapter<MotocicletasAdapter.ElementoViewHolder> {

    private final List<Motocicletas> listaElementos;

    public MotocicletasAdapter(List<Motocicletas> listaElementos) {
        this.listaElementos = listaElementos;
    }

    @Override
    public ElementoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.motocicletas_elementos, parent, false);
        return new ElementoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementoViewHolder holder, int position) {
        Motocicletas elemento = listaElementos.get(position);
        holder.titulo.setText(elemento.getTitulo());
        holder.contenido.setText(elemento.getContenido());
        holder.imagen.setImageResource(elemento.getImagenId());
        holder.direccionWeb.setText(elemento.getDireccionWeb());
        holder.telefono.setText(elemento.getTelefono());
        holder.radioButton.setChecked(elemento.isSeleccionado());
        holder.ratingBar.setRating(elemento.getPuntuacion());
    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public static class ElementoViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, contenido, direccionWeb, telefono;
        ImageView imagen;
        RadioButton radioButton;
        RatingBar ratingBar,puntuacion;

        public ElementoViewHolder(View itemView) {
            super(itemView);
            // Inicializar las vistas utilizando los IDs del layout item_elemento.xml
            titulo = itemView.findViewById(R.id.tituloElemento);
            contenido = itemView.findViewById(R.id.contenidoElemento);
            direccionWeb = itemView.findViewById(R.id.direccionWebElemento);
            telefono = itemView.findViewById(R.id.telefonoElemento);
            imagen = itemView.findViewById(R.id.imagenElemento);
            puntuacion = itemView.findViewById(R.id.puntuacionElemento);
        }
    }
}