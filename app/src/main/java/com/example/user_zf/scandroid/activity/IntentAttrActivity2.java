package com.example.user_zf.scandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user_zf.scandroid.R;

public class IntentAttrActivity2 extends Activity {

    TextView intentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_attr);

        intentContent = (TextView) findViewById(R.id.intentContent);
        intentContent.setText(getIntent().toString());
    }
}
