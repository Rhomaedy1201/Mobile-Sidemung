package com.rippleInv.sidemung.Model;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {
    @POST("/api/login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


    @POST("/api/pengaduan/insert")
    Call<PengaduanInsertResponse> pengaduan(@Body PengaduanInsertRequest pengaduanInsertRequest);
//    Call<PengaduanInsertResponse> pengaduan(
//            @Part MultipartBody.Part image,
////            @Part("image") RequestBody image,
//            @Part("alamat") RequestBody alamat,
//            @Part("description") RequestBody description,
//            @Part("judul") RequestBody judul,
//            @Part("user_nik") RequestBody user_nik
//            );

    @Multipart
    @POST("/api/pengaduan/upload_image")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);

    @GET("/api/logout")
    Call<ResponseBody> logout();


    @GET("api/pengaduan/{status}")
    Call<List<Pengaduan>> daftarPengaduan(@Path("status") String status);


    @POST("api/user/update_profile")
    Call<ResponseBody> updateProfile(@Body EditProfileRequest editProfileRequest);

    @DELETE("api/pengaduan/delete/{id}")
    Call<ResponseBody> deletePengaduan(@Path("id") String id);


}
