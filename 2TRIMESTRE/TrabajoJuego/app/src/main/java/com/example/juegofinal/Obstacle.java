package com.example.juegofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle {
    private int x, y, width, height, speed;
    private Rect hitbox;
    private Paint paint;

    public Obstacle(int startX, int screenHeight) {
        this.width = 80;
        this.height = 50;
        this.speed = 10;

        // Calcular la altura del suelo dinámicamente
        int groundHeight = screenHeight - 150; // Ajusta 150 según el tamaño del personaje y margen

        this.x = startX;
        this.y = groundHeight - height; // Ubicar el obstáculo en el suelo

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
