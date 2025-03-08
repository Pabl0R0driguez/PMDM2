package com.example.juegofinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer; // Importa MediaPlayer
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;
    private ArrayList<Obstacle> obstacles;
    private Paint paint;
    private Player player;
    private int score;
    private boolean isGameOver;
    private long lastObstacleTime;
    private long lastMonedaTime; // Para controlar la aparición de monedas
    private Bitmap backgroundImage;
    private int groundY; // Altura del suelo

    private MediaPlayer mediaPlayer; // Para música de fondo
    private MediaPlayer gameOverPlayer; // Para música de Game Over

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);
        player = new Player(getContext(), 100, 700); // Ajusta la altura del jugador
        obstacles = new ArrayList<>();
        paint = new Paint();
        score = 0;
        isGameOver = false;
        groundY = 700; // Establece la altura del suelo

        // Cargar la imagen de fondo y ajustarla a la pantalla
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.fondo2);

        // Inicializa los reproductores de audio
        mediaPlayer = MediaPlayer.create(context, R.raw.ancaramessi);
        mediaPlayer.setLooping(true); // Reproduce en bucle
        mediaPlayer.start(); // Comienza a reproducir música de fondo
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // No se necesita implementar
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.setRunning(false);
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Detener la música de fondo
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void update() {
        if (isGameOver) return;

        player.update();

        long currentTime = System.currentTimeMillis();
        // Generar obstáculos
        if (currentTime - lastObstacleTime > 2500) {
            boolean isMoving = new Random().nextBoolean(); // Aleatoriamente decide si el obstáculo se moverá o no
            obstacles.add(new Obstacle(getContext(), getWidth(), groundY, isMoving)); // Pasa isMoving al constructor
            lastObstacleTime = currentTime;
        }



        // Actualizar obstáculos
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            obstacles.get(i).update();
            if (obstacles.get(i).getX() < -100) {
                obstacles.remove(i);
                score++;
            }
        }



        // Verificar colisiones con obstáculos
        for (Obstacle obs : obstacles) {
            if (player.collidesWith(obs)) {
                Log.d("Collision", "Colisión detectada!"); // Log para depurar
                isGameOver = true; // Fin del juego
                playGameOverMusic(); // Reproduce música de Game Over
                break;
            }
        }
    }

    private void playGameOverMusic() {
        // Detener la música de fondo
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        // Reproduce la música de Game Over
        gameOverPlayer = MediaPlayer.create(getContext(), R.raw.siuu);
        gameOverPlayer.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump(); // Salta siempre igual al tocar
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas == null) return;

        // Dibuja la imagen de fondo
        canvas.drawBitmap(Bitmap.createScaledBitmap(backgroundImage, getWidth(), getHeight(), false), 0, 0, null);

        // Dibuja el jugador primero
        player.draw(canvas);

        // Luego dibuja los obstáculos
        for (Obstacle obs : obstacles) {
            obs.draw(canvas);
        }


        // Dibuja el puntaje
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Puntos: " + score, 50, 100, paint);

// Dibuja el mensaje de Game Over
        if (isGameOver) {
            // Establecer el color de fondo y dibujar un rectángulo

            // Establecer el texto
            paint.setColor(Color.RED);
            paint.setTextSize(80);
            paint.setShadowLayer(10, 5, 5, Color.BLACK); // Añade una sombra al texto

            // Calcular el ancho y alto del texto para centrarlo
            String gameOverText = "Has perdido";
            float textWidth = paint.measureText(gameOverText);
            float x = (getWidth() - textWidth) / 2; // Centra el texto horizontalmente
            float y = getHeight() / 2; // Mantiene el texto en el centro vertical

            // Dibuja el texto de Game Over
            canvas.drawText(gameOverText, x, y, paint);
        }

    }
    }
