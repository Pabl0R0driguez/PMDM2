package com.example.juegofinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
    private int x, y;
    private Rect hitbox;
    private Bitmap bitmap;
    private int size = 100; // Tamaño del jugador
    private float velocityY; // Velocidad en el eje Y
    private boolean isJumping; // Estado de salto

    public Player(Context context, int startX, int startY) {
        this.x = startX;
        this.y = startY; // Ajusta esta línea para hacer que el jugador esté más bajo
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.messi); // Reemplaza con el nombre de tu imagen
        this.hitbox = new Rect(x, y - size, x + size, y); // Inicializa el hitbox
    }

    public void update() {
        // Actualiza la posición Y del jugador por gravedad
        if (isJumping) {
            y += velocityY;
            velocityY += 2; // Aumentar la gravedad para caer más rápido
            if (y >= 850) { // Ajusta esta línea para definir el suelo
                y = 850; // Restablece la posición en el suelo
                isJumping = false; // El jugador ya no está saltando
                velocityY = 0; // Restablece la velocidad
            }
        }

        // Actualiza el hitbox basado en la posición del jugador
        hitbox.set(x, y - size, x + size, y); // Actualiza el hitbox con el tamaño actual
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true; // Cambia el estado a saltando
            velocityY = -40; // Aumentar la velocidad inicial del salto para saltar más alto
        }
    }

    public boolean collidesWith(Obstacle obs) {
        return Rect.intersects(hitbox, obs.getHitbox()); // Verifica la colisión
    }



    public void draw(Canvas canvas) {
        // Dibuja el jugador usando la imagen
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, size, size, false), x, y - size, null); // Ajusta el tamaño de la imagen si es necesario
    }

    public Rect getHitbox() {
        return hitbox; // Retorna el hitbox
    }

    public void setPosition(int x, int y) {
        this.x = x; // Asegúrate de tener una variable 'x' en tu clase Player
        this.y = y; // Asegúrate de tener una variable 'y' en tu clase Player
    }


    public int getY() {
        return y; // Devuelve la posición Y del jugador
    }
}
