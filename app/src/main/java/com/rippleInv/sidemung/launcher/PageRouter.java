package com.rippleInv.sidemung.launcher;

import android.content.Context;
import android.content.Intent;

public class PageRouter {

    public static void launch(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
