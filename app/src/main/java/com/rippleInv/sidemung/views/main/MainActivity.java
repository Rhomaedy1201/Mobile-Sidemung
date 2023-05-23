package com.rippleInv.sidemung.views.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rippleInv.sidemung.Fragment_recap.BelumDiProsesFragment;
import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.databinding.ActivityMainBinding;
import com.rippleInv.sidemung.views.Pengaduan.PengaduanActivity;
import com.rippleInv.sidemung.views.auth.Login;
import com.rippleInv.sidemung.views.home.HomeFragment;
import com.rippleInv.sidemung.views.notification.notifFragment;
import com.rippleInv.sidemung.views.profile.ProfileFragment;
import com.rippleInv.sidemung.views.recap.recapFragment;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
    ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // menggunakan binding.getRoot() untuk mengakses root view
        replaceFragment(new HomeFragment());
//        binding.bottomNavigationView = findViewById(R.id.bottomNavigationView);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.beranda:
                                replaceFragment(new HomeFragment());
                                return true;
                            case R.id.rekapan:
                                replaceFragment(new recapFragment());
                                return true;
                            case R.id.notif:
                                replaceFragment(new notifFragment());
                                return true;
                            case R.id.profil:
                                replaceFragment(new ProfileFragment());
                                return true;
                        }
                        return false;
                    }
                });
    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}