package com.example.user_zf.scandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user_zf.scandroid.R;

public class IntentTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
    }

    public void toActivity(View source){
        Intent intent = new Intent();
        intent.setAction("zfisll");
        intent.addCategory("gaga");
        intent.setType("abc/xyz");
        startActivity(intent);
    }

    public void startBluestone(View source){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.miui.gallery");
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void editContact(View source){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_EDIT);
        String data = "content://com.android.contacts/contacts/1";
        intent.setData(Uri.parse(data));
        startActivity(intent);
    }

    public void dialWife(View source){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        String data = "tel:13971975662";
        intent.setData(Uri.parse(data));
        startActivity(intent);
    }
}
