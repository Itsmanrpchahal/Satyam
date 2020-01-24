package com.mandy.satyam.baseclass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFrag extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("Usertoken", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void setStringVal(String key,String val){
        editor.putString(key,val);
        editor.apply();
    }

    public String getStringVal(String key){
        return sharedPreferences.getString(key,"");
    }

    void clearStringVal(String key)
    {
        editor.clear();
        editor.apply();
    }
}
