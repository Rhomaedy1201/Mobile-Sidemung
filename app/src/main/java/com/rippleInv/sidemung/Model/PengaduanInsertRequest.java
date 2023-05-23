package com.rippleInv.sidemung.Model;

import com.google.gson.annotations.SerializedName;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public class PengaduanInsertRequest {

    @SerializedName("image")
    private File image;

    @SerializedName("user_nik")
    private String user_nik;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("judul")
    private String judul;

    @SerializedName("description")
    private String description;


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getUser_nik() {
        return user_nik;
    }

    public void setUser_nik(String user_nik) {
        this.user_nik = user_nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
