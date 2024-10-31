package com.example.pablo_prueba2t5;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageView imageView = findViewById(R.id.imageView);

        TranslateAnimation animation = new TranslateAnimation(-1000, 1000, 0, 0);
        animation.setDuration(1100);

        imageView.startAnimation(animation);
    }
}

