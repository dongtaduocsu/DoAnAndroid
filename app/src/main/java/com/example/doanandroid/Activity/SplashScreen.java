package com.example.doanandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanandroid.R;

public class SplashScreen extends AppCompatActivity {
private TextView tv;
private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv = (TextView) findViewById(R.id.txt);
        iv = (ImageView)findViewById(R.id.img);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.my_transition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);
        final Intent myIntent = new Intent(this, MainActivity.class);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(myIntent);
                finish();
            }
        }, 5000);
           // timer.start();
    }
}
