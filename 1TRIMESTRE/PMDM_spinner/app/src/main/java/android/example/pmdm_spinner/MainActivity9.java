package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        TextView textView = findViewById(R.id.textView);
        Button boton = findViewById(R.id.boton);
        SeekBar seekBar = findViewById(R.id.seekbar);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progreso = seekBar.getProgress();
                int maximo = seekBar.getMax();

                if(progreso < maximo){
                    seekBar.setProgress(progreso + 1);
                }
                if(seekBar.getProgress() == 10){
                    textView.setText("Barra Completada");
                }
            }
        });

    }
}