package com.example.user_zf.scandroid.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Toast;

import com.example.user_zf.scandroid.R;



public class SkinPreActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        showCurrentMode();
    }

    /**
     * 显示当前模式
     */
    public void showCurrentMode(){
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                Toast.makeText(this, "白天模式", Toast.LENGTH_SHORT);
            case Configuration.UI_MODE_NIGHT_YES:
                Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT);
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                Toast.makeText(this, "不知道模式", Toast.LENGTH_SHORT);
        }
    }

    public void toSetting(View source){
        startActivity(new Intent(this, AssetDrawableActivity.class));
        this.finish();
    }
}
