package android.example.aplicaciongestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginMain extends AppCompatActivity {

    private List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_usuarios);

        // Referencias a los elementos de la interfaz
        EditText nombreUsuario = findViewById(R.id.nombreUsuario);
        EditText claveUsuario = findViewById(R.id.claveUsuario);
        Button botonIniciarSesion = findViewById(R.id.botonIniciarSesion);

        // Inicializar lista de usuarios válidos
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("1", "1"));
        listaUsuarios.add(new Usuario("usuario1", "clave1"));
        listaUsuarios.add(new Usuario("usuario2", "clave2"));

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUsuario.getText().toString();
                String clave = claveUsuario.getText().toString();

                if (validarUsuario(nombre, clave)) {
                    // Ir a la pantalla principal
                    Intent intent = new Intent(LoginMain.this, MainMotocicletas.class);
                    startActivity(intent);
                    finish();
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_personalizado, findViewById(R.id.customToastContainer));

                    TextView text = layout.findViewById(R.id.toastText);
                    text.setText("¡Introduce datos válidos!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            }

            private boolean validarUsuario(String nombre, String clave) {
                for (Usuario usuario : listaUsuarios) {
                    if (usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave)) {
                        return true;
                    }
                }
                return false;
            }
        }); // Cierre correcto
    }
}