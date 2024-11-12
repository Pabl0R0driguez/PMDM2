package android.example.aplicaciongestion;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainMotocicletas extends AppCompatActivity {

    private ListView listaMotos;
    private MotocicletasAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motocicletas_main);

        // Inicializar el RecyclerView
        listaMotos = findViewById(R.id.listaMotocicletas); // Cambia el ID al del RecyclerView en tu layout

        // Crear y cargar la lista de motocicletas
        List<Motocicletas> listaMotocicletas = new ArrayList<>();
        listaMotocicletas.add(new Motocicletas("Yamaha MT-07", "Moto de alta gama", "https://susuki.com", "3333333", 3, true, 3));
        listaMotocicletas.add(new Motocicletas("Honda CBR 500R", "Moto deportiva y potente", "https://honda.com/image.jpg", "4444444", 4, false, 5));
        listaMotocicletas.add(new Motocicletas("Kawasaki Ninja 400", "Moto ligera y rápida", "https://kawasaki.com/ninja400.jpg", "5555555", 5, true, 4));
        listaMotocicletas.add(new Motocicletas("Ducati Panigale V2", "Moto de carreras", "https://ducati.com/panigaleV2.jpg", "6666666", 5, true, 5));
        listaMotocicletas.add(new Motocicletas("BMW S1000RR", "Moto deportiva de alto rendimiento", "https://bmw.com/s1000rr.jpg", "7777777", 5, false, 4));

        // Configurar el Adaptador para el RecyclerView
        adaptador = new MotocicletasAdapter(listaMotocicletas);
        listaMotos.setAdapter((ListAdapter) adaptador);
    }
}
