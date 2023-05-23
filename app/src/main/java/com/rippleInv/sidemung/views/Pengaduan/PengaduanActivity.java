package com.rippleInv.sidemung.views.Pengaduan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.github.drjacky.imagepicker.ImagePicker;
//import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.google.android.material.textfield.TextInputEditText;
import com.rippleInv.sidemung.Model.ApiClient;
import com.rippleInv.sidemung.Model.ImageRequestBody;
import com.rippleInv.sidemung.Model.LoginRequest;
import com.rippleInv.sidemung.Model.LoginResponse;
import com.rippleInv.sidemung.Model.MyPreferences;
import com.rippleInv.sidemung.Model.PengaduanInsertRequest;
import com.rippleInv.sidemung.Model.PengaduanInsertResponse;
import com.rippleInv.sidemung.Model.UserService;
import com.rippleInv.sidemung.R;
import com.rippleInv.sidemung.RestApi.LinkApi;
import com.rippleInv.sidemung.views.auth.Login;
import com.rippleInv.sidemung.views.auth.Register;
import com.rippleInv.sidemung.views.home.HomeFragment;
import com.rippleInv.sidemung.views.main.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanActivity extends AppCompatActivity {

    Button btn_pengaduan;
    String path;
    MyPreferences preferences;
    Uri uri;
    private ImageView gambar_pengaduan;
    private TextInputEditText alamat,name,phone,judul,description;
    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Toolbar toolbar = findViewById(R.id.action_barPengaduan);
        toolbar.setTitle("Pengaduan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         preferences = new MyPreferences(PengaduanActivity.this);

        btn_pengaduan = findViewById(R.id.btnPengaduan);
        gambar_pengaduan = findViewById(R.id.gambarPengaduan);
        alamat = findViewById(R.id.inputlocation);
        judul = findViewById(R.id.incident);
        phone = findViewById(R.id.inputHp);
        name = findViewById(R.id.inputNama);
        description = findViewById(R.id.Dcomplain);

        name.setText(preferences.getString("name",""));
        phone.setText(preferences.getString("phone",""));


        btn_pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPengaduan();
            }
        });

        gambar_pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
//                ImagePicker.Companion.with(PengaduanActivity.this)
//                        .crop()
//                        .cropOval()
//                        .maxResultSize(512,512,true)
//                        .provider(ImageProvider.BOTH) //Or bothCameraGallery()
//                        .createIntentFromDialog((Function1)(new Function1(){
//                            public Object invoke(Object var1){
//                                this.invoke((Intent)var1);
//                                return Unit.INSTANCE;
//                            }
//
//                            public final void invoke(@NotNull Intent it){
//                                Intrinsics.checkNotNullParameter(it,"it");
////                                launcher.launch(it);
//                            }
//                        }));
            }
        });
    }


    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"),101);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101 && resultCode == Activity.RESULT_OK){
//            uri = data.getData();
//            gambar_pengaduan.setImageURI(uri);
//        }else{
//            Toast.makeText(getApplicationContext(), "No Image", Toast.LENGTH_SHORT).show();
//        }
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                gambar_pengaduan.setImageBitmap(bitmap);
                selectedImage = bitmap;
                File imageFile = convertBitmapToFile(selectedImage);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);
                Call<ResponseBody> call = ApiClient.getUserService(PengaduanActivity.this).uploadImage(imagePart);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.raw());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File convertBitmapToFile(Bitmap bitmap) {
        File file = new File(getCacheDir(), "image.jpg");
        try {
            file.createNewFile();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bitmapData = byteArrayOutputStream.toByteArray();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bitmapData);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void postPengaduan(){

        File imageFile = convertBitmapToFile(selectedImage);

        // Create the request body
        PengaduanInsertRequest pengaduanInsertRequest = new PengaduanInsertRequest();
        pengaduanInsertRequest.setAlamat(alamat.getText().toString());
        pengaduanInsertRequest.setJudul(judul.getText().toString());
        pengaduanInsertRequest.setDescription(description.getText().toString());
        pengaduanInsertRequest.setUser_nik(preferences.getString("nik",""));

        Call<PengaduanInsertResponse> pengaduanInsertResponseCall = ApiClient.getUserService(PengaduanActivity.this).pengaduan(pengaduanInsertRequest);
        pengaduanInsertResponseCall.enqueue(new Callback<PengaduanInsertResponse>() {
            @Override
            public void onResponse(Call<PengaduanInsertResponse> call, Response<PengaduanInsertResponse> response) {
                System.out.println(response.raw());
                if (response.isSuccessful()){
                    startActivity(new Intent(PengaduanActivity.this,MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PengaduanInsertResponse> call, Throwable t) {

            }
        });
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);
        ImageRequestBody imageRequestBody = new ImageRequestBody(imageFile);


//        File imageFile = new File("path/to/image.jpg");

        // Create the request body
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

        // Send the request
//        Call<ResponseBody> call = ApiClient.getUserService(PengaduanActivity.this).uploadImage(imagePart);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                System.out.println(response.raw());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

//        Create the request body and multipart part
//        RequestBody imageBody = RequestBody.create(MediaType.parse("image/jpeg"),imageFile);
//        MultipartBody.Part imagePart = imageRequestBody.getMultipart();
//        RequestBody alamatBody = RequestBody.create(MediaType.parse("text/plain"),
//                alamat.getText().toString());
//        RequestBody judulBody = RequestBody.create(MediaType.parse("text/plain"),
//                judul.getText().toString());
//        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"),
//                description.getText().toString());
//        RequestBody nikBody = RequestBody.create(MediaType.parse("text/plain"),
//                preferences.getString("nik",""));
//        Call<PengaduanInsertResponse> pengaduanInsertResponseCall = ApiClient.getUserService(PengaduanActivity.this).pengaduan(
//                imagePart,alamatBody,descBody,judulBody,nikBody
//        );
//        pengaduanInsertResponseCall.enqueue(new Callback<PengaduanInsertResponse>() {
//            @Override
//            public void onResponse(Call<PengaduanInsertResponse> call, Response<PengaduanInsertResponse> response) {
////                PengaduanInsertResponse pengaduanInsertResponse = response.body();
//                    System.out.println(response.raw());
//            }
//
//            @Override
//            public void onFailure(Call<PengaduanInsertResponse> call, Throwable t) {
//                System.err.println(t.getMessage());
//            }
//        });
    }

}