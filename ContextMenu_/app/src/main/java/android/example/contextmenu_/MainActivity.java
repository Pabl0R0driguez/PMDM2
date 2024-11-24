package android.example.contextmenu_;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView texto1;
    TextView texto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        texto1= findViewById(R.id.texto1);
        texto2= findViewById(R.id.texto2);
        registerForContextMenu(texto1);
        registerForContextMenu(texto2);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Verificar cuál vista activó el menú contextual
        if (v.getId() == R.id.texto1) {
            getMenuInflater().inflate(R.menu.menu1, menu);
        } else if (v.getId() == R.id.texto2) {
            getMenuInflater().inflate(R.menu.menu2, menu);

        }
    }
}