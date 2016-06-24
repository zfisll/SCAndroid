package com.example.user_zf.scandroid.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.user_zf.scandroid.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user_zf on 16/6/23.
 * 从完全透明 -> 完全不透明的渐变图片
 */
public class AlphaImageView extends ImageView {

    int speed = 30;//0.3秒改变一次
    float allAlpha = 255;//最终透明度
    float curAlpha = 0;//当前透明度
    float perAlpha;//每次增加的透明度

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x123){
                curAlpha += perAlpha;
                if(curAlpha > allAlpha){
                    curAlpha = allAlpha;
                }
                AlphaImageView.this.setAlpha(curAlpha/allAlpha);
                Log.d("zf_tag", curAlpha+"");
            }
        }
    };

    public AlphaImageView(Context context){
        super(context);
    }

    public AlphaImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlphaImageView);
        int duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0);
        perAlpha = 255*speed / duration;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.setAlpha(0f);
        super.onDraw(canvas);
        Timer timer = new Timer();
        //计时器周期性发送消息，改变图片透明度
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(curAlpha >= allAlpha){
                    cancel();
                }
                handler.sendEmptyMessage(0x123);
            }
        }, 0, speed);
    }
}
