package com.mandy.satyam.utils;


import android.app.Activity;

import com.google.android.material.snackbar.Snackbar;


public class Snack {
    public static Snackbar snackbar(Activity context, String string) {
        Snackbar snackbar = Snackbar.make(context.findViewById(android.R.id.content), string, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }
}
