package android.example.actividad11_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CustomView extends View {
    private Paint paint;
    private String apellido = "Rodríguez Segura";
    private Random random;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Coordenadas base
        int x = 200;
        int y = 300;

        for (int i = 0; i < 5; i++) {
            // Cambiar tamaño de fuente
            paint.setTextSize(random.nextInt(60) + 30);

            // Cambiar color de fuente
            paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

            // Cambiar alineación de texto
            switch (random.nextInt(3)) {
                case 0:
                    paint.setTextAlign(Paint.Align.LEFT);
                    break;
                case 1:
                    paint.setTextAlign(Paint.Align.CENTER);
                    break;
                case 2:
                    paint.setTextAlign(Paint.Align.RIGHT);
                    break;
            }

            // Cambiar inclinación de la fuente
            paint.setTypeface(random.nextBoolean() ? Typeface.DEFAULT_BOLD : Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));

            // Escalado y rotación aleatoria
            canvas.save();
            canvas.scale(1 + random.nextFloat(), 1 + random.nextFloat());
            canvas.rotate(random.nextInt(20) - 10, x, y);

            // Dibujar el apellido
            canvas.drawText(apellido, x, y + (i * 120), paint);
            canvas.restore();
        }
    }
}
