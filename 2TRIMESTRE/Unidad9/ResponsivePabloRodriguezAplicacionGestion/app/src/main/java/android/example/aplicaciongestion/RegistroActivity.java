package android.example.aplicaciongestion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private UsuarioCRUD usuarioDAO;
    private EditText nombreUsuario;
    private EditText contraseña;
    private ImageButton btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity); // Asegúrate de que este es el nombre correcto de tu layout

        // Inicializa el DAO
        usuarioDAO = new UsuarioCRUD(this);
        usuarioDAO.open();

        // Inicializa las vistas
        nombreUsuario = findViewById(R.id.nombreUsuario);
        contraseña = findViewById(R.id.contraseña);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configura el botón de guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUsuario.getText().toString().trim();
                String clave = contraseña.getText().toString().trim();

                if (!nombre.isEmpty() && !clave.isEmpty()) {
                    long id = usuarioDAO.insertarUsuario(nombre, clave);
                    if (id != -1) {
                        Toast.makeText(RegistroActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        finish(); // Cierra la actividad después de registrar
                    } else {
                        Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistroActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usuarioDAO.close();
    }
}
