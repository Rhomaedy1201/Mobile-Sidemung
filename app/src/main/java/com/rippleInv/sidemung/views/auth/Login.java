package com.rippleInv.sidemung.views.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.LoginRequest;
import com.rippleInv.sidemung.Model.LoginResponse;
import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.RestApi.LinkApi;
import com.rippleInv.sidemung.launcher.PageRouter;
import com.rippleInv.sidemung.views.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private TextInputEditText password,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.et_username);
        password = findViewById(R.id.et_pw);

        Toolbar toolbar = findViewById(R.id.action_barLogin);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        action();

    }
    private void action(){
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
//                if (password.getText() == null || password.getText().equals("")){
//                    System.out.println("Masih kosong");
//                }else{
//                    Toast.makeText(getApplicationContext(), "tidak kosong", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void login(){
        String inputPass = password.getText().toString().trim();
        String inputPhone = phone.getText().toString().trim();
        System.out.println(inputPass +" "+inputPhone);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhone(inputPhone);
        loginRequest.setPassword(inputPass);

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService(Login.this).userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Login.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    // Save data to MyPreferences
                    MyPreferences preferences = new MyPreferences(Login.this);
                    LoginRequest loginRequest = loginResponse.getData().getUser();
//                    System.out.println(loginResponse.getData().getToken());
                    preferences.saveString("token",loginResponse.getData().getToken());
                    preferences.saveString("nik",loginResponse.getData().getUser().getNik());
                    preferences.saveString("email",loginResponse.getData().getUser().getEmail());
                    preferences.saveString("name",loginResponse.getData().getUser().getName());
                    preferences.saveString("phone",loginResponse.getData().getUser().getPhone());
                    System.out.println(preferences.getString("token",""));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            preferences.setLoggedInUser(TampilanLogin.this, loginRequest.getNim());
//                            preferences.setLoggedInStatus(true);
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }
                    }, 500);

                } else {
                    Toast.makeText(Login.this, "User tidak ditemukan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(TampilanLogin.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}