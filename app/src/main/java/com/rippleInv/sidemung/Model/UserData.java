package com.rippleInv.sidemung.Model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("user")
    private LoginRequest user;

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public LoginRequest getUser() {
        return user;
    }
}
