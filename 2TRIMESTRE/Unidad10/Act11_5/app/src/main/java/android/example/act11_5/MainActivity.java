package android.example.act11_5;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int sound1, sound2;
    private float volume = 0.5f; // Volumen inicial
    private float rate = 1.0f;   // Velocidad inicial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        // Cargar sonidos
        sound1 = soundPool.load(this, R.raw.gato, 1);
        sound2 = soundPool.load(this, R.raw.laser, 1);

        // Referencias UI
        Button btnSound1 = findViewById(R.id.btnSound1);
        Button btnSound2 = findViewById(R.id.btnSound2);
        SeekBar seekVolume = findViewById(R.id.seekVolume);
        SeekBar seekRate = findViewById(R.id.seekRate);

        // Configurar SeekBars
        seekVolume.setMax(200);
        seekVolume.setProgress(100);
        seekRate.setMax(200);
        seekRate.setProgress(100);

        // Listener del volumen
        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = progress / 200f;
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Listener de la velocidad
        seekRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rate = progress / 100f;
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Botón 1 - Reproducir sonido 1
        btnSound1.setOnClickListener(v -> soundPool.play(sound1, volume, volume, 1, 0, rate));

        // Botón 2 - Reproducir sonido 2
        btnSound2.setOnClickListener(v -> soundPool.play(sound2, volume, volume, 1, 0, rate));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
