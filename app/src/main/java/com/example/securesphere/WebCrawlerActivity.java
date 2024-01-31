package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WebCrawlerActivity extends AppCompatActivity {

    ImageButton exit_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_crawler);

        exit_bt = findViewById(R.id.exit_menu);


        exit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WebCrawlerActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}