package android.example.pmdm_spinner;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        // Enlazar el Switch y el TextView
// Enlazar los Switches de cada día de la semana
        Switch switchMonday = findViewById(R.id.switchMonday);
        Switch switchTuesday = findViewById(R.id.switchTuesday);
        Switch switchWednesday = findViewById(R.id.switchWednesday);
        Switch switchThursday = findViewById(R.id.switchThursday);
        Switch switchFriday = findViewById(R.id.switchFriday);
        Switch switchSaturday = findViewById(R.id.switchSaturday);
        Switch switchSunday = findViewById(R.id.switchSunday);
        TextView textViewStatus = findViewById(R.id.texto);

        // Configurar el listener del Switch
        switchMonday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                textViewStatus.setText("Estado: ON");
            } else {
                textViewStatus.setText("Estado: OFF");
            }
        });
    }

    }
