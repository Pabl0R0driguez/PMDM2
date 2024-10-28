package android.example.pmdm_spinner;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        CheckBox lunes = findViewById(R.id.lunes);
        CheckBox martes = findViewById(R.id.martes);
        CheckBox miercoles = findViewById(R.id.miercoles);
        CheckBox jueves = findViewById(R.id.jueves);
        CheckBox viernes = findViewById(R.id.viernes);
        CheckBox sabado = findViewById(R.id.sabado);
        CheckBox domingo = findViewById(R.id.domingo);

        TextView texto = findViewById(R.id.texto);

        //Lunes
        lunes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String lunestexto = lunes.getText().toString();
                texto.setText(lunestexto);

            }
        });

        //Martes
        martes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String martestexto = martes.getText().toString();
                texto.setText(martestexto);
            }
        });

        //Miércoles
        miercoles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String miercolestexto = miercoles.getText().toString();
                texto.setText(miercolestexto);
            }
        });

        //Jueves
        jueves.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String juevestexto = jueves.getText().toString();
                texto.setText(juevestexto);
            }
        });

        //Viernes
        viernes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String viernestexto = viernes.getText().toString();
                texto.setText(viernestexto);

            }
        });

        //Sábado
        sabado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String sabadotexto = sabado.getText().toString();
                texto.setText(sabadotexto);
            }
        });

        //Domingo
        domingo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String domingotexto = domingo.getText().toString();
                texto.setText(domingotexto);
            }
        });




    }
}