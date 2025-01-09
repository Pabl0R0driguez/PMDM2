package android.example.pmdm_spinner;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView textView = findViewById(R.id.textview);
        CheckBox checkBoxSuma = findViewById(R.id.suma);
        CheckBox checkBoxResta = findViewById(R.id.resta);
        EditText texto1 = findViewById(R.id.texto1);
        EditText texto2 = findViewById(R.id.texto2);

        // Listener para la suma
        checkBoxSuma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realizarCalculo(texto1, texto2, checkBoxSuma.isChecked(), checkBoxResta.isChecked(), textView);
            }
        });

        // Listener para la resta
        checkBoxResta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                realizarCalculo(texto1, texto2, checkBoxSuma.isChecked(), checkBoxResta.isChecked(), textView);
            }
        });
    }

    //Método para hacer el calculo, le pasamos como parametros los edittext, el textview y los checkbox booleanos
    private void realizarCalculo(EditText texto1, EditText texto2, boolean isSumaChecked, boolean isRestaChecked, TextView textView) {
        String num1 = texto1.getText().toString();
        String num2 = texto2.getText().toString();

        //Comprobamos que no este vacio
        if (!num1.isEmpty() && !num2.isEmpty()) {

            //Passamos el texto a entero
            int numero1 = Integer.parseInt(num1);
            int numero2 = Integer.parseInt(num2);
            StringBuilder resultado = new StringBuilder();

            if (isSumaChecked) {
                resultado.append("Suma: ").append(numero1 + numero2).append("\n");
            }
            if (isRestaChecked) {
                resultado.append("Resta: ").append(numero1 - numero2).append("\n");
            }

            textView.setText(resultado.toString());
        } else {
            textView.setText("Por favor, ingrese ambos números.");
        }
    }
}
