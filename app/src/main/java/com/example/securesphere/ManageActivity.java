package com.example.securesphere;

/* App By Kiruthik Suriyah M
   Date Written: 29-Jan-2024 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ManageActivity extends AppCompatActivity {

    ImageButton exit_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        exit_activity = findViewById(R.id.exit_menu);

        exit_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManageActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}