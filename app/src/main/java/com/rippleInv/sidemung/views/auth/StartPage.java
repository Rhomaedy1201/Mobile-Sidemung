package com.rippleInv.sidemung.views.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.launcher.PageRouter;

public class StartPage extends AppCompatActivity {
    private Button btnToLogin, btnToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        action();
    }
    private void action(){
        btnToLogin = findViewById(R.id.btnToLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        btnToLogin.setOnClickListener(v ->{
            PageRouter.launch(this,Login.class);
        });

        btnToRegister.setOnClickListener(v->{
            PageRouter.launch(this,Register.class);
        });

    }
}