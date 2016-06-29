package com.example.user_zf.scandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user_zf on 16/6/28.
 * SharedPreference工具
 */
public class SPUtils {

    public static final String MY_FILE = "zf_file";

    public static void putBoolean(Context context, String name, boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_FILE, Activity.MODE_PRIVATE).edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String name){
        SharedPreferences sp = context.getSharedPreferences(MY_FILE, Activity.MODE_PRIVATE);
        return sp.getBoolean(name, false);
    }
}
