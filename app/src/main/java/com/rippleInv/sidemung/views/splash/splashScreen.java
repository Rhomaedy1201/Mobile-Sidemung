package com.rippleInv.sidemung.views.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.rippleInv.sidemung.views.auth.StartPage;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.launcher.PageRouter;

public class splashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3500; // 3,5 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        getActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PageRouter.launch(splashScreen.this, StartPage.class);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}