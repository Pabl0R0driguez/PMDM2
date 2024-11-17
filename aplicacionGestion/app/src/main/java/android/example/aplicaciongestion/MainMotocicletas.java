package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainMotocicletas extends AppCompatActivity {

    private ListView listaMotos;
    private MotocicletasAdapter adaptador;
    private List<Motocicletas> listaMotocicletas;
    private Button botonWebView;  // Definir el Button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motocicletas_main);

        // Inicializar ListView
        listaMotos = findViewById(R.id.listaMotocicletas);

        // Inicializar el Button
        botonWebView = findViewById(R.id.button2);  // Aquí inicializas el botón

        // Crear y cargar la lista de motocicletas
        listaMotocicletas = new ArrayList<>();
        listaMotocicletas.add(new Motocicletas("Yamaha MT-07", "Moto de alta gama", 3, "https://honda.com/image.jpg", "623176354", R.drawable.moto1, true));
        listaMotocicletas.add(new Motocicletas("Kawasaki Ninja ZX-10R", "Moto deportiva con excelente rendimiento, ideal para los amantes de la velocidad", 5, "https://kawasaki.com/ninja-zx10r.jpg", "623176355", R.drawable.moto2, false));
        listaMotocicletas.add(new Motocicletas("Honda CBR 600RR", "Moto deportiva de alto rendimiento, famosa por su manejo ágil y preciso", 4, "https://honda.com/cbr600rr.jpg", "623176356", R.drawable.moto3, true));
        listaMotocicletas.add(new Motocicletas("Ducati Panigale V4", "Moto de alta gama con diseño italiano y tecnología avanzada, ideal para los entusiastas del motociclismo", 5, "https://ducati.com/panigale-v4.jpg", "623176357", R.drawable.moto4, false));

        // Configurar el Adaptador para el ListView
        adaptador = new MotocicletasAdapter(this, listaMotocicletas);
        listaMotos.setAdapter(adaptador); // Usar directamente el adaptador con la lista

        // Configurar el botón para abrir la actividad WebView_Act
        botonWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad WebView_act cuando el botón es presionado
                Intent intent = new Intent(MainMotocicletas.this, WebView_act.class);
                startActivity(intent);
            }
        });
    }
}
