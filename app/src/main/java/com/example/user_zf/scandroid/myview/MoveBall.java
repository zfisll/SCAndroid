package com.example.user_zf.scandroid.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user_zf on 16/6/30.
 * 移动的小球
 */
public class MoveBall extends View {

    Paint paint;

    int centerX;
    int centerY;

    final int radius = 40;

    public MoveBall(Context context){
        super(context);
        centerX = 40;
        centerY = 40;
    }

    public MoveBall(Context context, AttributeSet attrs){
        super(context, attrs);
        centerX = 40;
        centerY = 40;
    }

    public void setCenter(int centerX, int centerY){
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化画笔
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //画球
        canvas.drawCircle(centerX, centerY, radius, paint);
    }
}
