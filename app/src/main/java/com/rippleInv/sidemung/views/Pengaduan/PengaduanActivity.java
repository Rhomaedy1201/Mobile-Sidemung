package com.rippleInv.sidemung.views.Pengaduan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.RestApi.LinkApi;
import com.rippleInv.sidemung.views.auth.Login;
import com.rippleInv.sidemung.views.auth.Register;
import com.rippleInv.sidemung.views.home.HomeFragment;
import com.rippleInv.sidemung.views.main.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class PengaduanActivity extends AppCompatActivity {

    Button btn_pengaduan;
    String path;
    Uri uri;
    private ImageView gambar_pengaduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.action_barPengaduan);
        toolbar.setTitle("Pengaduan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_pengaduan = findViewById(R.id.btnPengaduan);
        gambar_pengaduan = findViewById(R.id.gambarPengaduan);

        btn_pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPengaduan();
            }
        });

        gambar_pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(PengaduanActivity.this)
                        .crop()
                        .cropOval()
                        .maxResultSize(512,512,true)
                        .provider(ImageProvider.BOTH) //Or bothCameraGallery()
                        .createIntentFromDialog((Function1)(new Function1(){
                            public Object invoke(Object var1){
                                this.invoke((Intent)var1);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(@NotNull Intent it){
                                Intrinsics.checkNotNullParameter(it,"it");
//                                launcher.launch(it);
                            }
                        }));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            gambar_pengaduan.setImageURI(uri);
        }else{
            Toast.makeText(getApplicationContext(), "No Image", Toast.LENGTH_SHORT).show();
        }
    }

    public void postPengaduan(){
//        String inputNik = nik.getText().toString().trim();
//        String inputName = name.getText().toString().trim();
//        String inputEmail = email.getText().toString().trim();
//        String inputPhone = phone.getText().toString().trim();
//        String inputPassword = password.getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LinkApi.PostPengaduan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Rest response",response);
//                nik.setText("");
//                name.setText("");
//                email.setText("");
//                phone.setText("");
//                password.setText("");
                Toast.makeText(getApplicationContext(), "Registrasi Menambahkan Pengaduan", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PengaduanActivity.this, MainActivity.class);
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
                params.put("image", "gambar.jpg");
                params.put("user_nik", "1231231234");
                params.put("judul", "Ular dalam rumah");
                params.put("user_id", "1");
                params.put("description", "Ada ular dalam rumah");
                params.put("alamat", "Bondowoso");
                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Content-Type", "application/form-data");
                return params;
            }

        };

        requestQueue.add(stringRequest);
    }

}