package com.example.juegofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.Random;

public class Obstacle {
    private int x, y, width, height, speed;
    private int originalY;
    private int amplitude = 50;
    private int oscillationSpeed = 5;
    private int oscillationCounter = 0;
    private boolean isMoving;

    private Rect hitbox;
    private Paint paint;

    public Obstacle(int startX, int playerY) {
        this.width = 80;
        this.height = 50;
        this.speed = 10;

        this.x = startX;
        this.originalY = playerY - (height / 2);
        this.y = originalY;

        this.hitbox = new Rect(x, y, x + width, y + height);
        this.paint = new Paint();
        paint.setColor(Color.BLUE);

        Random random = new Random();
        isMoving = random.nextBoolean();
    }

    public void update(float speedMultiplier) {
        x -= speed * speedMultiplier;

        if (isMoving) {
            y = originalY + (int) (Math.sin(Math.toRadians(oscillationCounter)) * amplitude);
            oscillationCounter += oscillationSpeed;
        }

        hitbox.set(x, y, x + width, y + height);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(hitbox, paint);
    }

    public int getX() {
        return x;
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
