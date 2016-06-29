package com.example.user_zf.scandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user_zf.scandroid.R;

public class SkinPreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

    }

    public void toSetting(View source){
        startActivity(new Intent(this, AssetDrawableActivity.class));
        this.finish();
    }
}
