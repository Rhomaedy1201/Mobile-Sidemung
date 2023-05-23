package com.rippleInv.sidemung.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private  String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("name")
    private String name;

    @SerializedName("nik")
    private String nik;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
