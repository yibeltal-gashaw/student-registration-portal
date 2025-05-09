package com.estifatech.labexam.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class Prefs {
    SharedPreferences preferences;
    Context context;
    public Prefs(Context context){
        this.context = context;
    }
    public void saveUid(String uid){
        preferences = context.getSharedPreferences("user_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uid",uid);
        editor.apply();
    }
    public String getUid(){
        preferences = context.getSharedPreferences("user_pref",MODE_PRIVATE);
        return preferences.getString("uid",null);
    }
}
