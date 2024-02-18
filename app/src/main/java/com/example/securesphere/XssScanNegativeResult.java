package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class XssScanNegativeResult extends AppCompatActivity {

    LottieAnimationView animationView;
    Button ok_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xss_scan_negative_result);

        animationView = findViewById(R.id.feedClearAni2);
        ok_bt = findViewById(R.id.ok_bt_negative);
        ok_bt.setVisibility(View.GONE);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ok_bt.setVisibility(View.VISIBLE);
                animationView.pauseAnimation();
            }
        }, 1300);

        ok_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(XssScanNegativeResult.this, XssActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}