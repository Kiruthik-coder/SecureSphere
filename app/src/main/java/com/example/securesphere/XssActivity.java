package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class XssActivity extends AppCompatActivity {

    ImageButton exit_bt;

    Button scan_bt;
    TextView history_txt;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_xss);
        history_txt = findViewById(R.id.textView16);
        scan_bt = findViewById(R.id.p1b2);
        exit_bt = findViewById(R.id.exit_xss);
        exit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(XssActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        animationView = findViewById(R.id.animationView2);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                history_txt.setText("No Data");
                animationView.pauseAnimation();
            }
        }, 5000);


        scan_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(XssActivity.this, XssScanningActivity.class);
                startActivity(i);
            }
        });

    }
}