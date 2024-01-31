package com.example.securesphere;

/* App By Kiruthik Suriyah M
   Date Written: 27-Jan-2024 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.widget.Toast;
import com.example.securesphere.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                .addToBackStack(null)
                .commit();
    }
}