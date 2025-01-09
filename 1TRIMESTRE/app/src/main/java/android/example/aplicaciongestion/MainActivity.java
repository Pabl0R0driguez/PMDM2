package android.example.aplicaciongestion;

import android.example.aplicaciongestion.Elemento;
import android.example.aplicaciongestion.ElementoAdapter;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ElementoAdapter adapter;
    private List<Elemento> listaElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewElementos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear una lista de ejemplo
        listaElementos = new ArrayList<>();
        listaElementos.add(new Elemento("Yamaha MT-07", "Motocicleta versátil de 689cc", R.drawable.pablo, 4, "444444444"));


        // Configurar el Adapter
        adapter = new ElementoAdapter(listaElementos);
        recyclerView.setAdapter(adapter);
    }
}
