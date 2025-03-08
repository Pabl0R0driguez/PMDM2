package com.example.juegofinal;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this)); // Inicia el juego
    }
}