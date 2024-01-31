package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    ImageButton exit_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_left);
        setContentView(R.layout.activity_menu);

        exit_bt = findViewById(R.id.exit_menu);

        exit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}