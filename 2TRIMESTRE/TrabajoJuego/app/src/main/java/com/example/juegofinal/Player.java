package com.example.juegofinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
    private int x, y;
    private int velocity;
    private boolean isJumping;
    private Rect hitbox;

    private Bitmap spriteSheet;
    private Bitmap[] frames;
    private int currentFrame;
    private int frameWidth, frameHeight;
    private long lastFrameChangeTime;
    private int frameSpeed = 100; // Velocidad de animación (milisegundos por frame)

    public Player(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        this.velocity = 0;
        this.isJumping = false;
        this.hitbox = new Rect(x, y, x + 100, y + 100);

        // Cargar el spritesheet completo
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.run);

        // Suponiendo que hay 8 imágenes en una fila
        int totalFrames = 8;
        frameWidth = spriteSheet.getWidth() / totalFrames;
        frameHeight = spriteSheet.getHeight();

        // Extraer cada frame en un array
        frames = new Bitmap[totalFrames];
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = Bitmap.createBitmap(spriteSheet, i * frameWidth, 0, frameWidth, frameHeight);
        }

        currentFrame = 0;
        lastFrameChangeTime = System.currentTimeMillis();
    }

    public void update() {
        if (isJumping) {
            velocity += 1; // Simulación de gravedad
            y += velocity;
            if (y >= 600) { // Suelo (no dejar que el personaje pase de la altura 600)
                y = 600;
                isJumping = false;
                velocity = 0;
            }
            hitbox.set(x, y, x + frameWidth, y + frameHeight);
        }

        // Actualizar animación
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameChangeTime > frameSpeed) {
            currentFrame = (currentFrame + 1) % frames.length;
            lastFrameChangeTime = currentTime;
        }
    }

    public void jump() {
        if (!isJumping) {
            velocity = -20; // Salto (velocidad negativa para ascender)
            isJumping = true;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(frames[currentFrame], x, y, null);
    }

    public boolean collidesWith(Obstacle obs) {
        return Rect.intersects(hitbox, obs.getHitbox());
    }
}
