package android.example.p65_e3_5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent ejemplo = new Intent(Intent.ACTION_VIEW);
        ejemplo.setData(Uri.parse("https://www.sportium.es/register?cid=registro-250-verificar&gclid=Cj0KCQjwmt24BhDPARIsAJFYKk24Vw9tPQbIfLqGebVZRltXM7Aj5la9KouNvj-fQAULnRIcSzuFZeUaAoB4EALw_wcB"));
        startActivity(ejemplo);
    }


}
