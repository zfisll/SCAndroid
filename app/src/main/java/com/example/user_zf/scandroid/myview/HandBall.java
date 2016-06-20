package com.example.user_zf.scandroid.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.user_zf.scandroid.R;

/**
 * Created by user_zf on 16/6/20.
 * 跟随手移动的小球
 * note：1、onTouchEvent的使用
 *       2、onTouchEvent事件的传递：监听->view.callback->parent.callback
 */
public class HandBall extends View{

    Paint paint;
    float currentX;
    float currentY;

    public HandBall(Context context){
        super(context);
        paint = new Paint();
        currentX = 50;
        currentY = 50;
    }

    public HandBall(Context context, AttributeSet atts){
        super(context, atts);
        paint = new Paint();
        currentX = 50;
        currentY = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        canvas.drawCircle(currentX, currentY, 30, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        currentX = event.getX();
        currentY = event.getY();
        invalidate();//重绘
        Log.d("zf_tag", "HandBall onTouchEvent callback methdo");
        return false;
    }
}
