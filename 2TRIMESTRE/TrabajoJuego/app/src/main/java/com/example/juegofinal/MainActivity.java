package com.example.juegofinal;

import android.app.Activity;
import android.os.Bundle;

import com.example.juegofinal.GameView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
