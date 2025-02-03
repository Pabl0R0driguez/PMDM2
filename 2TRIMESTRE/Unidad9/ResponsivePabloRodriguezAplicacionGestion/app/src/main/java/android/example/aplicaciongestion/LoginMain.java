package android.example.aplicaciongestion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginMain extends AppCompatActivity {

    private UsuarioCRUD usuarioDAO;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_usuarios);

        EditText nombreUsuario = findViewById(R.id.nombreUsuario);
        EditText claveUsuario = findViewById(R.id.claveUsuario);
        Button botonIniciarSesion = findViewById(R.id.botonIniciarSesion);

        usuarioDAO = new UsuarioCRUD(this);
        usuarioDAO.open();

        // Insertar usuarios predeterminados (solo una vez)
        if (usuarioDAO.obtenerTodosLosUsuarios().isEmpty()) {
            usuarioDAO.insertarUsuario("1", "1");
            usuarioDAO.insertarUsuario("usuario1", "clave1");
            usuarioDAO.insertarUsuario("usuario2", "clave2");
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.gato);
        mediaPlayer.setLooping(true); // Para que la música se repita en bucle
        mediaPlayer.start();
        // Referencia al TextView de registro
        TextView textRegistrar = findViewById(R.id.textRegistrar);

        textRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMain.this, RegistroActivity.class);
                startActivity(intent);
            }
        });


        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUsuario.getText().toString().trim();
                String clave = claveUsuario.getText().toString().trim();

                if (usuarioDAO.validarUsuario(nombre, clave)) {
                    Intent intent = new Intent(LoginMain.this, MainMotocicletas.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginMain.this, "Usuario o clave incorrecta", Toast.LENGTH_SHORT).show();
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
