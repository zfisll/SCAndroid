package com.example.user_zf.scandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.myview.HandBall;

public class MainActivity extends AppCompatActivity {

    HandBall hbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hbView = (HandBall) findViewById(R.id.hbView);
        hbView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("zf_tag", "HandBall OnTouchListener's onTouch method");
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.d("zf_tag", "Activity onTouchEvent callback method");
        //消费事件，阻止时间继续传播
        return true;
    }
}
