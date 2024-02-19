package com.example.securesphere;

/* App By Kiruthik Suriyah M
   last updated: 30-Jan-2024 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_Activity extends AppCompatActivity {
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        bt1 = findViewById(R.id.p1b);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Welcome_Activity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}