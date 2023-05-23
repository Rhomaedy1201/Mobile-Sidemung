package com.rippleInv.sidemung.views.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.EditProfileRequest;
import com.rippleInv.sidemung.Model.LoginResponse;
import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.views.auth.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditprofileActivity extends AppCompatActivity {

    EditText name,no_hp,password;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.action_barEditProfile);
        toolbar.setTitle("Edit Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.uploadName);
        no_hp = findViewById(R.id.uploadHP);
        password = findViewById(R.id.uploadPassword);
        saveButton = findViewById(R.id.saveButton);

        MyPreferences preferences = new MyPreferences(EditprofileActivity.this);
        name.setText(preferences.getString("name",""));
        no_hp.setText(preferences.getString("phone",""));
        name.setEnabled(false);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAkun();
            }
        });
    }

private void updateAkun() {
    EditProfileRequest editProfileRequest = new EditProfileRequest();
    String inputPass = password.getText().toString().trim();
    String inputPhone = no_hp.getText().toString().trim();
    editProfileRequest.setPhone(inputPhone);
    editProfileRequest.setPassword(inputPass);
    Call<ResponseBody> responseBodyCall = ApiClient.getUserService(EditprofileActivity.this).updateProfile(editProfileRequest);
    responseBodyCall.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            MyPreferences preferences = new MyPreferences(EditprofileActivity.this);
            preferences.clear();
            System.out.println(response.raw());
            Intent intent = new Intent(EditprofileActivity.this, Login.class);
            startActivity(intent);
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
}}