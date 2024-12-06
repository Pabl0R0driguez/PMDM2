package android.example.actividad8_2;

import android.example.actividad8_2.Adaptador;
import android.example.actividad8_2.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Referencias al TabLayout y ViewPager
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager paginador = findViewById(R.id.contenedor2);

        // Configurar adaptador para ViewPager
        Adaptador adaptador = new Adaptador(getSupportFragmentManager(), 3); // Número de tabs
        paginador.setAdapter(adaptador);

        // Sincronizar ViewPager con TabLayout
        tabLayout.setupWithViewPager(paginador);

        // Configurar títulos para los tabs
        tabLayout.getTabAt(0).setText("Primero");
        tabLayout.getTabAt(1).setText("Segundo");
        tabLayout.getTabAt(2).setText("Tercero");
    }



}