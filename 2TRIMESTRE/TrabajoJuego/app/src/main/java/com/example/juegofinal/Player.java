package com.example.juegofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player {
    private int x, y;
    private int radius = 20;
    private Paint paint;
    private Rect hitbox;

    private boolean isJumping = false;
    private int velocity = 0;
    private int gravity = 1;
    private int jumpStrength = -12; // 🔹 Salto fijo
    private int groundY;

    private float speedMultiplier = 1.0f;
    private float speedIncreaseRate = 0.002f;
    private float maxSpeedMultiplier = 2.0f;

    public Player(int x, int groundY) {
        this.x = x;
        this.y = groundY;
        this.groundY = groundY;
        paint = new Paint();
        paint.setColor(Color.RED);
        hitbox = new Rect(x - radius, y - radius, x + radius, y + radius);
    }

    public void update() {
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocity = 0;
            }
        }

        if (speedMultiplier < maxSpeedMultiplier) {
            speedMultiplier += speedIncreaseRate;
        }

        hitbox.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void jump() {
        if (!isJumping) {
            velocity = jumpStrength; // 🔹 Salto SIEMPRE igual
            isJumping = true;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public boolean collidesWith(Obstacle obs) {
        return Rect.intersects(hitbox, obs.getHitbox());
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public int getY(){
        return y;
}

}