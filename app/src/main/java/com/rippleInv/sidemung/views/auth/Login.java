package com.rippleInv.sidemung.views.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;

import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.launcher.PageRouter;
import com.rippleInv.sidemung.views.main.MainActivity;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.action_barLogin);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        action();
    }
    private void action(){
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v->{
            PageRouter.launch(this, MainActivity.class);
        });
    }
}