package com.example.act11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnRecord, btnPause, btnPlay;
    private TextView tvStatus;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String audioFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones y texto
        btnRecord = findViewById(R.id.btnRecord);
        btnPause = findViewById(R.id.btnStop);
        btnPlay = findViewById(R.id.btnPlay);
        tvStatus = findViewById(R.id.tvStatus);

        // Ruta del archivo de audio
        audioFilePath = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/audio_record.3gp";

        // Solicitar permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        // Evento para grabar
        btnRecord.setOnClickListener(v -> startRecording());

        // Evento para detener grabación
        btnPause.setOnClickListener(v -> pauseRecoriding());

        // Evento para reproducir
        btnPlay.setOnClickListener(v -> playRecording());
    }

    private void startRecording() {
        try {
            // Configuración de MediaRecorder
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            // Cambiar estado de UI
            tvStatus.setText("INICIANDO LA GRABACIÓN...");
            btnRecord.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);

            // Mostrar mensaje
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pauseRecoriding() {
        if (mediaRecorder != null) {
            mediaRecorder.pause();
            mediaRecorder.release();
            mediaRecorder = null;

            // Cambiar estado de UI
            tvStatus.setText("AUDIO GRABADO CORRECTAMENTE");
            btnRecord.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);

            // Mostrar mensaje
            Toast.makeText(this, "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    private void playRecording() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();

            // Cambiar estado de UI
            tvStatus.setText("REPRODUCIENDO AUDIO...");
            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();

            // Detectar fin de la reproducción
            mediaPlayer.setOnCompletionListener(mp -> {
                tvStatus.setText("GRABANDO SONIDOS");
                btnPlay.setVisibility(View.VISIBLE);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
