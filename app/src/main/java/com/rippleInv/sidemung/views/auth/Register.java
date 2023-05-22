package com.rippleInv.sidemung.views.auth;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextInputEditText email, phone, nik, password, passwordConfirm;
    private Button login;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        email = findViewById(R.id.tv_email);
        phone = findViewById(R.id.tv_phone);
        nik = findViewById(R.id.tv_nik);
        password = findViewById(R.id.tv_password);
        passwordConfirm = findViewById(R.id.tv_password_cofirm);
        login = findViewById(R.id.btnToLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUser();
//                if (password.getText().equals(passwordConfirm.getText())){
////                    postUser();
//                    System.out.println("masuk");
//                }else{
//                    Toast.makeText(getApplicationContext(), "Password Harus Sama", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void postUser(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LinkApi.Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Rest response",response);
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
                params.put("nik", "565766776666");
                params.put("name", "inputName");
                params.put("email", "inputEmail@gmail.com");
                params.put("phone", "085234654123");
                params.put("password", "inputPassword");
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