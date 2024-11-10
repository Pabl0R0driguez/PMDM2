package android.example.pmdm_spinner;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class radioButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        RadioButton boton1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton boton2 = findViewById(R.id.radio2);
        RadioButton boton3 = findViewById(R.id.radio3);
        TextView textView = findViewById(R.id.textView);




    }
}