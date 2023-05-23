package com.rippleInv.sidemung.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.rippleInv.sidemung.views.auth.Login;

public class MyPreferences {

    private static final String PREFS_NAME = "MyPrefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    static final String KEY_NIM_SEDANG_LOGIN = "nik_logedin";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";
    static final String KEY_TOKEN = "Token";

    public MyPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void setLoggedInUser(Login tampilanLogin, String nim){
        editor.putString(KEY_NIM_SEDANG_LOGIN, nim);
        editor.apply();
    }

    public boolean setLoggedInStatus(boolean status){
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status);
        editor.apply();
        return status;
    }

    public boolean getLoggedInStatus(){
        return sharedPreferences.getBoolean(KEY_STATUS_SEDANG_LOGIN, false);
    }

//    public String getLoggedInUser(TampilanUbahProfil tampilanUbahProfil){
//        return sharedPreferences.getString(KEY_NIM_SEDANG_LOGIN,"");
//    }

    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void clear() {
        editor.remove(KEY_NIM_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.remove("token");
        editor.clear();
        editor.apply();
    }
}
