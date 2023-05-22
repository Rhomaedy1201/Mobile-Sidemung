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

    private String link = "http://192.168.1.9:8000/api/register";
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

        postUser();

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

    private void postUser(){
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        StringRequest request = new StringRequest(Request.Method.POST, LinkApi.Register, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingPB.setVisibility(View.GONE);
//                nameEdt.setText("");
//                jobEdt.setText("");

                Toast.makeText(Register.this, "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject respObj = new JSONObject(response);

                    String name = respObj.getString("name");
                    String job = respObj.getString("job");

                    responseTV.setText("Name : " + name + "\n" + "Job : " + job);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("job", job);
                return params;
            }
        };
        queue.add(request);
    }
}