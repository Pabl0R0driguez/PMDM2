package android.example.act7_3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ToastSeek extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_seek);

        SeekBar seekBar = findViewById(R.id.seekBar);
        Button showToastButton = findViewById(R.id.showSeekBarToastButton);

        showToastButton.setOnClickListener(v -> {
            int yOffset = seekBar.getProgress();
            Toast toast = Toast.makeText(this, "Toast personalizado con SeekBar", Toast.LENGTH_SHORT);
            toast.setGravity(0, 0, yOffset);
            toast.show();
        });
    }
}
