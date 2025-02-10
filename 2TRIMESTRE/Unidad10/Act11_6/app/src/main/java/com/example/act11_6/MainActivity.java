package com.example.act11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.AccessNetworkConstants;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback
 {
    private ImageButton btnRecord, btnPause, btnPlay;

    private TextView tvStatus;

    private MediaRecorder grabador;
    private MediaPlayer reproductor;

    private String audioFilePath;

     private VideoView surfaceView;
     private SurfaceHolder surfaceHolder;

     private String ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones y texto
        btnRecord = findViewById(R.id.btnRecord);
        btnPause = findViewById(R.id.btnStop);
        btnPlay = findViewById(R.id.btnPlay);
        tvStatus = findViewById(R.id.tvStatus);

        ruta = Environment.getExternalStorageState() + "/mivideo.mp4";

        // Ruta del archivo de audio
        audioFilePath = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/audio_record.3gp";

        // Solicitar permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        //  Pantalla
        surfaceView = findViewById(R.id.surfacevView);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        // Configuramos el tipo de buffered
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
                grabador.setVideoEncoder(MediaRecorder.VideoEncoder.H263);
                grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                grabador.setOutputFile(ruta);

                try {
                    grabador.prepare();
                } catch (IOException e) {

                }
                grabador.start();

            }
        });
    }
     @Override
     public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if(grabador == null){
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
         grabador.release();
         reproductor.release();

     }
 }
