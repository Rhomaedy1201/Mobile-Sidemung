package com.rippleInv.sidemung.routing;

import android.content.Context;
import android.content.Intent;

public class Router {

    public static void start(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
