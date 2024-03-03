package com.example.securesphere;

/* App By Kiruthik Suriyah M
   Date Written: 27-Jan-2024 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.securesphere.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ImageButton menu_bt, search_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getAction() != null && getIntent().getAction().equals("OPEN_ACTIVITY")) {
            Intent intent = new Intent(HomeActivity.this, BiometricAuthenticatorActivity.class);
            startActivity(intent);
        }

        menu_bt = findViewById(R.id.menu_bt);
        menu_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        search_bt = findViewById(R.id.serach_button);
        search_bt.setVisibility(View.GONE);

        try {
            replaceFragment(new HomeFragment());

            binding.bottom.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.Home){
                    replaceFragment(new HomeFragment());
                } else if (id == R.id.Feed) {
                    replaceFragment(new FeedFragment());
                } else if (id == R.id.Profile) {
                    replaceFragment(new ProfileFragment());
                }
                return true;
            });
        } catch (Exception e) {
            Toast.makeText(HomeActivity.this,"Error 100",Toast.LENGTH_SHORT).show();
        }

    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.fadein,
                        R.anim.fadeout

                )
                .replace(R.id.Frg_1, fragment)
                .commit();
    }
}