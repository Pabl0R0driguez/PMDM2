package android.example.aplicaciongestion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ElementoAdapter extends RecyclerView.Adapter<ElementoAdapter.ElementoViewHolder> {

    private List<Elemento> listaElementos;

    // Constructor del Adapter
    public ElementoAdapter(List<Elemento> listaElementos) {
        this.listaElementos = listaElementos;
    }

    @Override
    public ElementoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elemento, parent, false);
        return new ElementoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ElementoViewHolder holder, int position) {
        Elemento elemento = listaElementos.get(position);
        holder.titulo.setText(elemento.getTitulo());
        holder.contenido.setText(elemento.getContenido());
        holder.puntuacion.setRating(elemento.getPuntuacion());
        holder.direccionWeb.setText(elemento.getDireccionWeb());
        holder.telefono.setText(elemento.getTelefono());



    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public static class ElementoViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, contenido, direccionWeb, telefono;
        RatingBar puntuacion;
        ImageView imagen;

        public ElementoViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloElemento);
            contenido = itemView.findViewById(R.id.contenidoElemento);
            puntuacion = itemView.findViewById(R.id.puntuacionElemento);
            direccionWeb = itemView.findViewById(R.id.direccionWebElemento);
            telefono = itemView.findViewById(R.id.telefonoElemento);
            imagen = itemView.findViewById(R.id.imagenElemento);
        }
    }
}
