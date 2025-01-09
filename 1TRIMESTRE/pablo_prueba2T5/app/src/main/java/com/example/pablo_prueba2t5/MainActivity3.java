package com.example.pablo_prueba2t5;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageView = findViewById(R.id.imageView);

        AlphaAnimation aparecer = new AlphaAnimation(0.0f, 1.0f);
        aparecer.setDuration(1300);

        imageView.startAnimation(aparecer);
    }

}