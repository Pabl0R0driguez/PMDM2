package android.example.act7_4;

import android.example.act7_4.R;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityDialogBotones extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_botones);

        Button showDialogButton = findViewById(R.id.showDialogButton);

        // Acción al presionar el botón para mostrar el cuadro de diálogo
        showDialogButton.setOnClickListener(v -> {
            // Configurar el cuadro de diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ACTIVO EN EL DIALOGO")
                    .setMessage("Seleccione una acción")
                    .setPositiveButton("ACEPTAR", (dialog, which) -> {
                        Toast.makeText(this, "Opción: ACEPTAR", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("CANCELAR", (dialog, which) -> {
                        Toast.makeText(this, "Opción: CANCELAR", Toast.LENGTH_SHORT).show();
                    })
                    .setNeutralButton("IGNORAR", (dialog, which) -> {
                        Toast.makeText(this, "Opción: IGNORAR", Toast.LENGTH_SHORT).show();
                    });

            // Mostrar el cuadro de diálogo
            builder.create().show();
        });
    }
}

