package com.rippleInv.sidemung.views.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.views.auth.Login;
import com.rippleInv.sidemung.views.auth.StartPage;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.launcher.PageRouter;
import com.rippleInv.sidemung.views.main.MainActivity;

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
                MyPreferences preferences = new MyPreferences(getApplicationContext());
                System.out.println(preferences.getString("token", ""));
                if (!preferences.getString("token","").equalsIgnoreCase("")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}