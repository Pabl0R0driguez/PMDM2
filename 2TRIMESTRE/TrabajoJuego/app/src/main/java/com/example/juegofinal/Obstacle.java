package com.example.juegofinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Obstacle {
    private int x, y;
    private Rect hitbox;
    private boolean isMoving; // Para saber si el obstáculo se mueve
    private float speed; // Velocidad de movimiento de los obstáculos que se mueven
    private int direction; // Dirección del movimiento: -1 (arriba) o 1 (abajo)
    private int size = 60; // Aumenta el tamaño del obstáculo
    private Bitmap bitmap; // Imagen del obstáculo
    private Random random; // Para generar posiciones aleatorias

    public Obstacle(Context context, int startX, int groundY, boolean isMoving) {
        this.x = startX;
        this.random = new Random();
        this.isMoving = isMoving;

        // Establece la posición y del obstáculo
        if (isMoving) {
            // Si el obstáculo se mueve, elige una posición aleatoria entre 400 y 700 para más movimiento
            this.y = 400 + random.nextInt(300); // Ajusta para que sea un rango adecuado
            this.direction = random.nextBoolean() ? -1 : 1; // Aleatorio arriba o abajo
            this.speed = 15; // Velocidad de movimiento
        } else {
            // Si no se mueve, colócalo más abajo en la pantalla
            this.y = groundY - size + 120; // Ajusta para que esté más bajo que el suelo
        }

        // Inicializa la hitbox
        this.hitbox = new Rect(x, y, x + size, y + size);

        // Cargar la imagen del obstáculo
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.balon); // Reemplaza con el nombre de tu imagen
    }

    public void update() {
        if (isMoving) {
            // Mover el obstáculo hacia arriba o hacia abajo
            y += direction * speed; // Cambia la posición y

            // Cambia de dirección si llega a los límites
            if (y <= 350 || y >= 750) { // Ajusta según el límite inferior y superior
                direction *= -1; // Cambia la dirección
            }
        }

        // Aumenta la velocidad de movimiento hacia la izquierda
        x -= 10; // Cambia este valor a uno mayor para hacer que los obstáculos avancen más rápido

        // Actualizar el hitbox del obstáculo
        hitbox.set(x, y, x + size, y + size); // Ajusta hitbox
    }

    public void draw(Canvas canvas) {
        // Dibuja el obstáculo usando la imagen
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, size, size, false), x, y, null); // Ajusta el tamaño de la imagen si es necesario
    }

    public Rect getHitbox() {
        return hitbox; // Retorna el hitbox
    }

    public int getX() {
        return x; // Retorna la posición x del obstáculo
    }
}
