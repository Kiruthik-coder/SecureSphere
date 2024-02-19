package com.example.securesphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class XssScanningActivity extends AppCompatActivity {

    LottieAnimationView animationView, load_ani;
    String result = "", URL_txt = "" ;

    TextView status, info, info_2;

    private Intent intent;

    public void getScanResults() {
        try {
            URL url = new URL("http://172.20.10.2:9999/predict");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(URL_txt.getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = in.readLine();
                in.close();
                result = response;
            }
        } catch (IOException e) {
            //Toast.makeText(XssScanningActivity.this, "Unable to connect to Server, Check your Internet Connection", Toast.LENGTH_LONG).show();
            if (intent == null){
            intent = new Intent(XssScanningActivity.this, XSSServerError.class);
            startActivity(intent);
            }
            //e.printStackTrace();
        }
    }

    public void scan() throws ExecutionException, InterruptedException {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                getScanResults();
                return null;
            }
            protected void onPostExecute(Boolean success) {
                if (success) {
                    // Connection successful
                } else {
                    // Connection unsuccessful
                    //Toast.makeText(XssScanningActivity.this, "Unable to connect to Server", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_xss_scanning);
        status = findViewById(R.id.status_info_sc);
        status.setVisibility(View.GONE);
        info = findViewById(R.id.info_txt_sc);
        info.setVisibility(View.GONE);
        animationView = findViewById(R.id.progressbar);
        animationView.setVisibility(View.GONE);
        load_ani = findViewById(R.id.loadani7);
        info_2 = findViewById(R.id.status_info_sc2);

        Bundle rs = getIntent().getExtras();
        URL_txt = rs.getString("URL");
        try {
            //animationView.pauseAnimation();
            scan();
        } catch (ExecutionException e) {
            //Toast.makeText(XssScanningActivity.this, "Unable to connect to Server", Toast.LENGTH_LONG).show();
            //finish();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            //Toast.makeText(XssScanningActivity.this, "Unable to connect to Server", Toast.LENGTH_LONG).show();
            //finish();
            throw new RuntimeException(e);
        }

        /*final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.pauseAnimation();
            }
        }, 1000);*/

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (result.equals("")){
                    if (intent == null){
                        intent = new Intent(XssScanningActivity.this, XSSServerError.class);
                        startActivity(intent);
                    }
                } else {
                    load_ani.setVisibility(View.GONE);
                    info_2.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                    status.setVisibility(View.VISIBLE);
                    info.setVisibility(View.VISIBLE);
                    final Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (result.equals("true")){
                                Intent i = new Intent(XssScanningActivity.this, XssScanNegativeResult.class);
                                startActivity(i);
                                finish();
                            }
                            if (result.equals("false")){
                                Intent i = new Intent(XssScanningActivity.this, XssScanPositiveResult.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }, 3250);
                }
            }
        }, 5000);

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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
