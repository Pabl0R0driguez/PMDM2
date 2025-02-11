package com.example.act11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Button btnRecord, btnPause, btnPlay;
    private TextView tvStatus;

    private MediaRecorder grabador;
    private MediaPlayer reproductor;

    private String audioFilePath;
    private boolean grabando = false;

    private VideoView surfaceView;
    private SurfaceHolder surfaceHolder;

    private String rutaVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones y texto
        btnRecord = findViewById(R.id.btnRecord);
        btnPause = findViewById(R.id.btnStop);
        btnPlay = findViewById(R.id.btnPlay);
        tvStatus = findViewById(R.id.tvStatus);
        surfaceView = findViewById(R.id.surfacevView);

        // Ruta del video y del audio
        rutaVideo = getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath() + "/mivideo.mp4";
        audioFilePath = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/audio_record.3gp";

        // Solicitar permisos si no están concedidos
        solicitarPermisos();

        // Configuración del SurfaceView
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        // Botón de grabar
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarGrabacion();
            }
        });

        // Botón de detener grabación o reproducción
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerGrabacion();
            }
        });

        // Botón de reproducir
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirVideo();
            }
        });
    }

    private void solicitarPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1000);
        }
    }

    private void iniciarGrabacion() {
        if (grabando) {
            Toast.makeText(this, "Ya estás grabando", Toast.LENGTH_SHORT).show();
            return;
        }

        grabador = new MediaRecorder();
        grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabador.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        grabador.setOutputFile(audioFilePath);

        try {
            grabador.prepare();
            grabador.start();
            grabando = true;
            tvStatus.setText("Grabando...");
            btnRecord.setEnabled(false);
            btnPause.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar grabación", Toast.LENGTH_SHORT).show();
        }
    }

    private void detenerGrabacion() {
        if (grabando) {
            grabador.stop();
            grabador.release();
            grabador = null;
            grabando = false;
            tvStatus.setText("Grabación detenida");
            btnRecord.setEnabled(true);
            btnPause.setEnabled(false);
        } else if (reproductor != null && reproductor.isPlaying()) {
            reproductor.stop();
            reproductor.release();
            reproductor = null;
            tvStatus.setText("Reproducción detenida");
        }
    }

    private void reproducirVideo() {
        reproductor = new MediaPlayer();
        try {
            reproductor.setDataSource(audioFilePath);
            reproductor.prepare();
            reproductor.start();
            tvStatus.setText("Reproduciendo...");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al reproducir el audio", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // Inicializar el grabador si es necesario
        if (grabador == null) {
            grabador = new MediaRecorder();
            grabador.setPreviewDisplay(holder.getSurface());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        if (reproductor == null) {
            reproductor = new MediaPlayer();
            reproductor.setDisplay(holder);
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (grabador != null) {
            grabador.release();
        }
        if (reproductor != null) {
            reproductor.release();
        }
    }
}
