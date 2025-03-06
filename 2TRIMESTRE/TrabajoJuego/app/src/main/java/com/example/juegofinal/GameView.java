package com.example.juegofinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int backgroundColor = Color.CYAN;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);
        player = new Player(100, 500);
        obstacles = new ArrayList<>();
        paint = new Paint();
        score = 0;
        isGameOver = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

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
    }

    public void update() {
        if (isGameOver) return;

        player.update();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastObstacleTime > 2500) { // 🔹 Obstáculos más separados
            obstacles.add(new Obstacle(getWidth(), player.getY()));
            lastObstacleTime = currentTime;
        }

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            obstacles.get(i).update(player.getSpeedMultiplier());
            if (obstacles.get(i).getX() < -100) {
                obstacles.remove(i);
                score++;
            }
        }

        for (Obstacle obs : obstacles) {
            if (player.collidesWith(obs)) {
                isGameOver = true;
                break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas == null) return;

        canvas.drawColor(backgroundColor);
        player.draw(canvas);

        for (Obstacle obs : obstacles) {
            obs.draw(canvas);
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("Puntos: " + score, 50, 100, paint);

        if (isGameOver) {
            paint.setTextSize(80);
            canvas.drawText("GAME OVER", getWidth() / 4, getHeight() / 2, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump(); // 🔹 Salta siempre igual al tocar
            return true;
        }
        return super.onTouchEvent(event);
    }

}
