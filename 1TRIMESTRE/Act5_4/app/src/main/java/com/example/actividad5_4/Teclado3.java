package com.example.actividad5_4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class Teclado3 extends AppCompatActivity {

    private EditText numInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teclado3);

        numInput = findViewById(R.id.textInput);


        // Implementación del OnEditorActionListener
        numInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Código que se ejecuta al pulsar el botón "Buscar" en el teclado
                    // Por ejemplo, iniciar una búsqueda
                    return true;
                }
                return false;
            }
        });

    }
}