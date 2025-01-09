package android.example.act7_4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityDialogLista extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_lista);

        Button showListDialogButton = findViewById(R.id.showListDialogButton);

        String[] options = {"LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES"};

        showListDialogButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ACTIVO EN EL DIALOGO")
                    .setItems(options, (dialog, which) -> {
                        String selectedOption = options[which];
                        Toast.makeText(this, "Seleccionaste: " + selectedOption, Toast.LENGTH_SHORT).show();
                    });

            builder.create().show();
        });
    }
}
