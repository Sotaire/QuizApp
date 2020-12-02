package com.tilek.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences sharedPreferences;
    public Prefs(Context context) {
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }
    public void setTheme(int theme){
        sharedPreferences.edit().putInt("isShown",theme).apply();
    }

    public int getTheme(){
        return sharedPreferences.getInt("isShown",0);
    }
}