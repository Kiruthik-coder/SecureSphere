package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class XssScanningActivity extends AppCompatActivity {

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_xss_scanning);

        animationView = findViewById(R.id.progressbar);

        //Need to pause and resume at contacting places...
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.pauseAnimation();
            }
        }, 1000);


        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.resumeAnimation();
            }
        }, 2000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(XssScanningActivity.this, XssScanResultActivity.class);
                startActivity(i);
                finish();
            }
        }, 4050);




    }
}