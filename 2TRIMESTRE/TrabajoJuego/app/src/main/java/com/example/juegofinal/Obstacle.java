package com.example.juegofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle {
    private int x, y, width, height, speed;
    private Rect hitbox;
    private Paint paint;

    public Obstacle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 80;
        this.height = 50;
        this.speed = 10;
        this.hitbox = new Rect(x, y, x + width, y + height);
        this.paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    public void update() {
        x -= speed; // Mover el obstáculo hacia la izquierda
        hitbox.set(x, y, x + width, y + height); // Actualizar la hitbox
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(hitbox, paint); // Dibujar el obstáculo
    }

    public int getX() {
        return x;
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
