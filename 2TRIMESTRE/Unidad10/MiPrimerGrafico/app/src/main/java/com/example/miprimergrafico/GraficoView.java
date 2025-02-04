package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class GraficoView extends View {

    private Paint paintVerde, paintRojo, paintTexto;
    private float width, height, escala;

    public GraficoView(Context context) {
        super(context);

        // Configurar Paint para la línea verde
        paintVerde = new Paint();
        paintVerde.setColor(Color.GREEN);
        paintVerde.setStrokeWidth(5);

        // Configurar Paint para la línea roja
        paintRojo = new Paint();
        paintRojo.setColor(Color.RED);
        paintRojo.setStrokeWidth(5);

        // Configurar Paint para los textos
        paintTexto = new Paint();
        paintTexto.setColor(Color.BLACK);
        paintTexto.setTextSize(40);

        // Obtener dimensiones del dispositivo
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        escala = metrics.density; // Escalado de pantalla
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float yVerde = 40 * escala;
        float xRojo = 20 * escala;

        // Dibujar líneas
        canvas.drawLine(0, yVerde, width, yVerde, paintVerde); // Línea verde horizontal
        canvas.drawLine(xRojo, 0, xRojo, height, paintRojo);  // Línea roja vertical

        // Dibujar textos con valores de pantalla y escala en la misma línea
        String textoDimensiones = "Ancho: " + width + " px  |  Alto: " + height + " px";
        canvas.drawText(textoDimensiones, 50, 100, paintTexto);

        // Dibujar el texto de Escala debajo
        canvas.drawText("Escala: " + escala, 50, 150, paintTexto);

    }
}
