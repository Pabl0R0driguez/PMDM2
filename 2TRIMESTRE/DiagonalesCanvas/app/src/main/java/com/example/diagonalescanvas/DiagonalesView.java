package com.example.diagonalesdelcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.DashPathEffect;
import android.util.DisplayMetrics;
import android.view.View;

public class DiagonalesView extends View {

    private Paint paintAzul;
    private float escala;

    public DiagonalesView(Context context) {
        super(context);

        // Configurar Paint para las líneas azules
        paintAzul = new Paint();
        paintAzul.setColor(Color.BLUE);
        paintAzul.setStrokeWidth(2 * escala);

        // Obtener el valor de escalado del dispositivo
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        escala = metrics.density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Obtener dimensiones del Canvas
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int bottom = getBottom(); // Obtener el valor de bottom

        // Fondo amarillo
        canvas.drawColor(Color.YELLOW);

        // Configurar Paint para líneas punteadas
        PathEffect effects = new DashPathEffect(new float[]{10, 10}, 0);
        paintAzul.setPathEffect(effects); // Aplicar efecto punteado

        // Dibujar las diagonales punteadas
        canvas.drawLine(0, 0, width, height, paintAzul);  // Diagonal ↘
        canvas.drawLine(0, height, width, 0, paintAzul);  // Diagonal ↗

        // Configurar Paint para texto
        Paint paintTexto = new Paint();
        paintTexto.setColor(Color.BLACK);

        // Escalar el tamaño del texto en función de la altura del canvas
        paintTexto.setTextSize(height * 0.05f); // Ajustar el tamaño del texto al 5% de la altura del canvas
        paintTexto.setTextAlign(Paint.Align.CENTER); // Centrar texto horizontalmente

        // Posición inicial para los textos
        int yPos = (int) (height * 0.1f); // 10% de la altura
        int espacio = (int) (height * 0.05f); // Espacio entre líneas del 5% de la altura

        // Dibujar textos uno debajo del otro
        canvas.drawText("Ancho: " + width + " px", width / 2, yPos, paintTexto);
        canvas.drawText("Alto: " + height + " px", width / 2, yPos + espacio, paintTexto);
        canvas.drawText("Bottom: " + bottom + " px", width / 2, yPos + (2 * espacio), paintTexto);
        canvas.drawText("Escala: " + escala, width / 2, yPos + (3 * espacio), paintTexto);
    }

}
