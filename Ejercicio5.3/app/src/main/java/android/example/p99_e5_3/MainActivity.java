package android.example.p99_e5_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

private String textooriginal = "No pulsado";
private String nuevotexto = "Pulsado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.button2);
        TextView texto =findViewById(R.id.texto);

        texto.setText(textooriginal);

        boton.setOnClickListener(v ->{

            if(texto.getText().equals(textooriginal)){
                texto.setTextColor(Color.RED);
                texto.setText(nuevotexto);

            }else{
                recreate();
            }
                });


        }
}