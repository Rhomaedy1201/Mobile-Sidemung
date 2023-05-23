package com.rippleInv.sidemung.Model;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class EditProfileRequest {


    @SerializedName("phone")
    private String phone;

    @SerializedName("password")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
