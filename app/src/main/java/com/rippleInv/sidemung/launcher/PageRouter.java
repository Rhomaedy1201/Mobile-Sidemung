package com.rippleInv.sidemung.launcher;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class PageRouter {

    public static void launch(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
    private Fragment fragment;

    public PageRouter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void start(Class<?> destinationActivity) {
        Intent intent = new Intent(fragment.requireContext(), destinationActivity);
        fragment.startActivity(intent);
    }
}