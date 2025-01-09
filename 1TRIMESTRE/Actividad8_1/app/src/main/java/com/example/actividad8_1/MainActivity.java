package com.example.actividad8_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que el archivo XML sea el correcto

        Toolbar toolbar = findViewById(R.id.toolbar); // Enlaza la ToolBar
        setSupportActionBar(toolbar); // Usa la ToolBar como ActionBar
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Retornar la creación del menú
        return super.onCreateOptionsMenu(menu);
    }

    // Método para mostrar mensajes con Toast
    private void mensaje(int resId) {
        Toast.makeText(this, getText(resId), Toast.LENGTH_SHORT).show();
    }

    // Sobrescribir el método onOptionsItemSelected para manejar las selecciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verificamos el ítem seleccionado con if-else
        if (item.getItemId() == R.id.action_add) {  // Opción Añadir
            mensaje(R.string.cadena_barra_nuevo);  // Mostrar mensaje para la opción Añadir
            return true;
        } else if (item.getItemId() == R.id.action_edit) {  // Opción Editar
            mensaje(R.string.cadena_barra_editar);  // Mostrar mensaje para la opción Editar
            return true;
        } else if (item.getItemId() == R.id.action_settings) {  // Opción Configurar
            mensaje(R.string.cadena_barra_configurar);  // Mostrar mensaje para la opción Configurar
            return true;
        } else if (item.getItemId() == R.id.action_help) {  // Opción Ayuda
            mensaje(R.string.cadena_barra_ayuda);  // Mostrar mensaje para la opción Ayuda
            return true;
        } else if (item.getItemId() == R.id.action_about) {  // Opción Acerca de
            mensaje(R.string.cadena_barra_acerca);  // Mostrar mensaje para la opción Acerca de
            return true;
        } else {
            return super.onOptionsItemSelected(item);  // Caso por defecto
        }
    }



}