package com.mandy.satyam.baseclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseClass extends AppCompatActivity {
    public AlertDialog alertDialog;
    public Context context;
    SharedPreferences sharedPreferences,welCOmeSharePref;
    SharedPreferences.Editor editor,editor1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("UserToken", Context.MODE_PRIVATE);
        welCOmeSharePref = getSharedPreferences("welcomePref",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor1 = welCOmeSharePref.edit();
    }


    public void showErrorDialog() {
        if (!isFinishing()) {
            alertDialog.show();
        }
    }

    public void setStringVal(String key,String val){
        editor.putString(key,val);
        editor.apply();
    }

    public void setWelComeString(String key,String val)
    {
        editor1.putString(key,val);
        editor1.apply();
    }

    public void clearStringVal(String key)
    {
        editor.clear();
        editor.apply();
    }

    public String getStringVal(String key){
        return sharedPreferences.getString(key,"");
    }

    public String getWelComeString(String key)
    {
        return welCOmeSharePref.getString(key,"");
    }

}
