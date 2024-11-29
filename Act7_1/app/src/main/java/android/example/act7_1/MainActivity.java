package android.example.act7_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenPreferences = findViewById(R.id.btnOpenPreferences);
        Button btnShowPreferences = findViewById(R.id.btnShowPreferences);

        // Abrir las preferencias
        btnOpenPreferences.setOnClickListener(v -> {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        });

        // Mostrar los valores guardados en Logcat
        btnShowPreferences.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            String username = preferences.getString("username", "No definido");
            boolean notifications = preferences.getBoolean("notifications", false);
            String color = preferences.getString("color", "No definido");

            Log.d("Preferencias", "Usuario: " + username);
            Log.d("Preferencias", "Notificaciones: " + notifications);
            Log.d("Preferencias", "Color: " + color);
        });
    }
}