package com.rippleInv.sidemung.views.Pengaduan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.views.home.HomeFragment;

public class PengaduanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.action_barPengaduan);
        toolbar.setTitle("Pengaduan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void backPengaduan(View view){
//        Intent intent = new Intent(PengaduanActivity.this, HomeFragment.class);
//        startActivity(intent);
    }
}