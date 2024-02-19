package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    TextView history_txt, URL_txt;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_xss);
        history_txt = findViewById(R.id.textView16);
        scan_bt = findViewById(R.id.infoBt);
        exit_bt = findViewById(R.id.exit_xss);
        URL_txt = findViewById(R.id.UrlTextView);
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
                String temp = URL_txt.getText().toString();
                if (!temp.isEmpty()){
                    boolean Connection = haveNetworkConnection();
                    if (!Connection){
                        Intent i = new Intent(XssActivity.this, NoInternetActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(XssActivity.this, XssScanningActivity.class);
                        i.putExtra("URL", temp);
                        startActivity(i);
                    }
                } else {
                    URL_txt.setHint("Please Paste URL !");
                }
            }
        });

    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}