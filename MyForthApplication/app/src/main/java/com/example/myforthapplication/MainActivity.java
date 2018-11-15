package com.example.myforthapplication;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        container.addView(new MyAnimationView(this));
        ImageButton voice_btn = findViewById(R.id.voice_btn);
        voice_btn.setImageResource(R.drawable.voice);
        Button btn = findViewById(R.id.btn_play);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlayGameActivity.class);
                startActivity(intent);
            }
        });
        Button btn_draw = findViewById(R.id.btn_draw);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DrawActivity.class);
                startActivity(intent);
            }
        });

        Button btn_change = findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PictureChangeActivity.class);
                startActivity(intent);
            }
        });
    }
    public class MyAnimationView extends View {
        public MyAnimationView(Context context){
            super(context);
            ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
                    .loadAnimator(MainActivity.this,R.animator.color_anim);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            objectAnimator.setTarget(this);
            objectAnimator.start();
        }
    }
}

