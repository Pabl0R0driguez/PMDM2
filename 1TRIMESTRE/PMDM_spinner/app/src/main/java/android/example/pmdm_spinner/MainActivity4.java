package android.example.pmdm_spinner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView textView = findViewById(R.id.textview);
        CheckBox checkBox1 = findViewById(R.id.suma);
        CheckBox checkBox2 = findViewById(R.id.resta);
        EditText texto1 = findViewById(R.id.texto1);
        EditText texto2 = findViewById(R.id.texto2);
        Button boton = findViewById(R.id.boton);



    boton.setOnClickListener(new View.OnClickListener() {
        //Método para dependiendo el checkbox que pulses haga una cosa u otra
        @Override
        public void onClick(View view) {

            String numero1 = texto1.getText().toString();
            String numero2 = texto2.getText().toString();

            if (checkBox1.isChecked()) {
               int num1 = Integer.parseInt(numero1);
               int num2 = Integer.parseInt(numero2);

              int resultado = num1 + num2;
              textView.setText("Suma" + resultado);
            }

            if (checkBox2.isChecked()) {
                int num1 = Integer.parseInt(numero1);
                int num2 = Integer.parseInt(numero2);

                int resultado = num1 - num2;
                textView.setText("Resta" + resultado);
            }

        }
    });

    }
}