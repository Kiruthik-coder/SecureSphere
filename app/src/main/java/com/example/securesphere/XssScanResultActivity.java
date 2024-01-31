package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class XssScanResultActivity extends AppCompatActivity {

    LottieAnimationView animationView;
    Button ok_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xss_scan_result);

        animationView = findViewById(R.id.safe_tick);
        ok_bt = findViewById(R.id.p1b7);

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
                Intent i = new Intent(XssScanResultActivity.this, XssActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}