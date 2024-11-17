package android.example.aplicaciongestion;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebView_act extends AppCompatActivity {

    private EditText editTextURL;
    private Button buttonLoad;
    private WebView webView;  // Cambiar tipo de 'webView' a WebView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        // Inicializar vistas
        editTextURL = findViewById(R.id.editTextURL);
        buttonLoad = findViewById(R.id.buttonLoad);
        webView = findViewById(R.id.webView);  // Inicialización del WebView

        // Configurar el WebView para navegar dentro de la app (sin abrir el navegador)
        webView.setWebViewClient(new WebViewClient());

        // Configurar el Button para cargar la URL en el WebView
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la URL desde el EditText
                String url = editTextURL.getText().toString().trim();

                // Verificar si la URL no está vacía
                if (!TextUtils.isEmpty(url)) {
                    // Cargar la URL en el WebView
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url;  // Asegurarse de que la URL sea válida
                    }
                    webView.loadUrl(url);  // Cargar la URL en el WebView
                }
            }
        });
    }
}
