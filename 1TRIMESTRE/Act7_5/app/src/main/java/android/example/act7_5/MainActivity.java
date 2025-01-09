package android.example.act7_5;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencia al botón desde el layout
        Button buttonPickDate = findViewById(R.id.boton);

        // Definir el OnClickListener para el botón
        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la fecha actual
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Crear el DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        (view, year1, month1, dayOfMonth1) -> {
                            // Mostrar la fecha seleccionada en un Toast
                            String selectedDate = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                            Toast.makeText(MainActivity.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
                        },
                        year, month, dayOfMonth); // Pasamos la fecha actual como predeterminada

                // Mostrar el DatePickerDialog
                datePickerDialog.show();
            }
        });
    }
}