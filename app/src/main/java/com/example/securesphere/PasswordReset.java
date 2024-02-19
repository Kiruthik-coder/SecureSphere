package com.example.securesphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    Button rest_link;
    EditText email;
    LottieAnimationView load_ani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        email = findViewById(R.id.email_resET_EDT);
        rest_link = findViewById(R.id.send_mail_bt);
        load_ani = findViewById(R.id.loadani6);

        load_ani.setVisibility(View.GONE);

        rest_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_ani.setVisibility(View.VISIBLE);
                rest_link.setVisibility(View.GONE);
                String mail = email.getText().toString();
                if (mail.equals("")){
                    load_ani.setVisibility(View.GONE);
                    rest_link.setVisibility(View.VISIBLE);
                    Toast.makeText(PasswordReset.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(mail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(PasswordReset.this, "Reset link sent, check your mail", Toast.LENGTH_SHORT).show();
                                        Log.d("Reset", "Email sent.");
                                        Intent i = new Intent(PasswordReset.this, LoginActivity.class);
                                        startActivity(i);
                                    }else {
                                        load_ani.setVisibility(View.GONE);
                                        rest_link.setVisibility(View.VISIBLE);
                                        Toast.makeText(PasswordReset.this, "Account not available", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}