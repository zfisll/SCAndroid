package com.example.user_zf.scandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user_zf.scandroid.myview.ManyShapeView;

/**
 * 本demo介绍handler的使用，在ui线程 和 其他线程中分别的使用
 * handler的用法：
 * 1、其他线程发送消息
 * 2、主线程处理消息
 */
public class ManyViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ManyShapeView view = new ManyShapeView(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(view);

        setContentView(layout);
    }
}
