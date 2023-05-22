package com.rippleInv.sidemung.views.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.RestApi.LinkApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private String TAG = "ActivityRegister";
    private TextInputEditText email, name, phone, nik, password, passwordConfirm;
    private Button login;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Toolbar toolbar = findViewById(R.id.action_barRegister);
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.tv_email);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        nik = findViewById(R.id.tv_nik);
        password = findViewById(R.id.tv_password);
        passwordConfirm = findViewById(R.id.tv_password_cofirm);
        login = findViewById(R.id.btnToLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUser();
//                if (password.getText() == null || password.getText().equals("")){
//                    System.out.println("Masih kosong");
//                }else{
//                    Toast.makeText(getApplicationContext(), "tidak kosong", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void postUser(){
        String inputNik = nik.getText().toString().trim();
        String inputName = name.getText().toString().trim();
        String inputEmail = email.getText().toString().trim();
        String inputPhone = phone.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LinkApi.Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Rest response",response);
                nik.setText("");
                name.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest response",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("nik", inputNik);
                params.put("name", inputName);
                params.put("email", inputEmail);
                params.put("phone", inputPhone);
                params.put("password", inputPassword);
                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

        };

        requestQueue.add(stringRequest);
    }
}