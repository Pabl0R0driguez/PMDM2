package com.example.juegofinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

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
        player = new Player(context, 100, 500); // Ajustado para que coincida con los obstáculos
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
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

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
        if (currentTime - lastObstacleTime > 1500) {
            obstacles.add(new Obstacle(getWidth(), 1000)); // Obstáculos a la misma altura que el personaje
            lastObstacleTime = currentTime;
        }

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            obstacles.get(i).update();
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

        if (score % 10 == 0) {
            backgroundColor = (backgroundColor == Color.CYAN) ? Color.DKGRAY : Color.CYAN;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas == null) return;

        canvas.drawColor(backgroundColor);
        player.draw(canvas); // Dibujar solo una vez

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
            player.jump();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
