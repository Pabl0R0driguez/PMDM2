package android.example.pablorodriguezsegurapruebatema11;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MediaPlayer mediaPlayer;

    // Lista de nombres de elementos
    String[] items = {"Sonido 1", "Sonido 2", "Sonido 3"};

    // Lista de archivos de audio en `res/raw`
    int[] audioFiles = {R.raw.laser, R.raw.gato, R.raw.laser};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Adaptador para la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Manejar clics en los elementos de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Si ya hay un audio reproduciéndose, detenerlo
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                // Crear y reproducir el audio correspondiente
                mediaPlayer = MediaPlayer.create(MainActivity.this, audioFiles[position]);
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar el MediaPlayer al cerrar la app
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
