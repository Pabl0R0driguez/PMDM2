package android.example.act7_3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ToastEdit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_edit);

        EditText inputText = findViewById(R.id.inputText);
        Button showToastButton = findViewById(R.id.showTextToastButton);

        showToastButton.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            if (!text.isEmpty()) {
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Por favor, escribe algo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
