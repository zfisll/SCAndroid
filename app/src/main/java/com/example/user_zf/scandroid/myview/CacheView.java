package com.example.user_zf.scandroid.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user_zf on 16/6/29.
 */
public class CacheView extends View {

    final int WIDTH = 600;
    final int HEIGHT = 1000;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;
    Paint paint;
    Path path;
    float prex;
    float prey;

    public CacheView(Context context, AttributeSet attrs){
        super(context, attrs);
        cacheBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //bitmap记录之前的画图
        canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        //path展示当前正在画的图
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                prex = x;
                prey = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(prex, prey, x, y);
                prex = x;
                prey = y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, paint);
                path.reset();
                break;
        }
        invalidate();
        //事件已经被消费，不会继续传递
        return true;
    }
}
