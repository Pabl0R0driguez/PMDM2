package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText visor;
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.visor);

        // Inicializar botones de números
        setNumberListener(R.id.button_0, "7");
        setNumberListener(R.id.button_1, "8");
        setNumberListener(R.id.button_2, "9");
        setNumberListener(R.id.button_4, "4");
        setNumberListener(R.id.button_5, "5");
        setNumberListener(R.id.button_6, "6");
        setNumberListener(R.id.button_8, "1");
        setNumberListener(R.id.button_9, "2");
        setNumberListener(R.id.button_10, "3");
        setNumberListener(R.id.button_11, "0");



        // Inicializar botones de operaciones
        setOperatorListener(R.id.button_3, "/");
        setOperatorListener(R.id.button_7, "*");
        setOperatorListener(R.id.button_mas, "+");
        setOperatorListener(R.id.button_menos, "-");

        // Botón de igual
        Button equalsButton = findViewById(R.id.button_igual);
        equalsButton.setOnClickListener(v -> calcularResultado());
    }

    private void setNumberListener(int buttonId, String value) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> visor.append(value));
    }

    private void setOperatorListener(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (!visor.getText().toString().isEmpty()) {
                num1 = Double.parseDouble(visor.getText().toString());
                operator = op;
                visor.setText("");
            }
        });
    }

    private void calcularResultado() {
        if (!visor.getText().toString().isEmpty()) {
            num2 = Double.parseDouble(visor.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        visor.setText("Error");
                        return;
                    }
                    break;
            }
            visor.setText(String.valueOf(result));
        }
    }
}
