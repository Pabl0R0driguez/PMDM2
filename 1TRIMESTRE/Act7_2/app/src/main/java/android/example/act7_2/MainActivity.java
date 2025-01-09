package android.example.act7_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.showToastButton).setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_c, findViewById(R.id.customToastContainer));

            TextView text = layout.findViewById(R.id.toastText);
            text.setText("¡Toast!");

            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        });
    }
}
