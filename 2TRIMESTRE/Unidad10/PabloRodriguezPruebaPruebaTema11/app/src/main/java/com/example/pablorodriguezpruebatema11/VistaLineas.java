package com.example.pablorodriguezpruebatema11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class VistaLineas extends View {
    private Paint lineaPaint;
    private Paint textoPaint;
    private float distancia;

    public VistaLineas(Context contexto, AttributeSet attributeSet) {
        super(contexto, attributeSet);
        init();
    }

    private void init() {
        distancia = getResources().getDisplayMetrics().scaledDensity;

        // Líneas discontinuas, configuración
        lineaPaint = new Paint();
        lineaPaint.setStyle(Paint.Style.STROKE);
        lineaPaint.setPathEffect(new DashPathEffect(new float[]{20, 10}, 0));
        lineaPaint.setColor(Color.GREEN);
        lineaPaint.setStrokeWidth(8);


        // Texto, configuración
        textoPaint = new Paint();
        textoPaint.setAntiAlias(true);
        textoPaint.setColor(Color.BLACK);
        textoPaint.setTextSize(20 * distancia);
    }

    // Configuración pantalla
    @Override
    protected void onDraw(Canvas lienzo) {
        super.onDraw(lienzo);
        lienzo.drawColor(Color.WHITE);
        float salto= 30 * distancia;
        int height = getHeight();
        int width = getWidth();


        // Dibujamos las líneas horizonatales
        for (float y = salto; y < height; y += salto) {
            Path path = new Path();
            path.moveTo(0, y); path.lineTo(width, y);
            lienzo.drawPath(path, lineaPaint);
            lienzo.drawText(String.format("%.4f", y), 10, y - 15, textoPaint);
        }

        // Información de las filas
        lienzo.drawText("fila -> 544 scale= " + distancia, 450, 544, textoPaint);
        lienzo.drawText("fila -> 1088 width= " + width, 450, 1088, textoPaint);
        lienzo.drawText("fila -> 1632 height= " + height, 450, 1632, textoPaint);
        lienzo.drawText("ratio -> " + ((float) height / width), 450, height - 10, textoPaint);
    }
}