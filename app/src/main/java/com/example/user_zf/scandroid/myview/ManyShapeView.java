package com.example.user_zf.scandroid.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.user_zf.scandroid.R;

/**
 * Created by user_zf on 16/6/28.
 * 绘制很多图形的View
 */
public class ManyShapeView extends View{

    public ManyShapeView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景为白色
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.background));
        //定义一个画笔
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        //圆
        canvas.drawCircle(100, 70, 50, paint);
        //矩形
        canvas.drawRect(50, 140, 150, 190, paint);
        //圆角矩形
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect(50, 210, 150, 260, 10, 10, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 50, 210, paint);
        }
        //椭圆
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawOval(50, 280, 150, 330, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 50, 210, paint);
        }
        //三角形
        Path trianglePath = new Path();
        trianglePath.moveTo(100, 350);
        trianglePath.lineTo(150, 400);
        trianglePath.lineTo(50, 400);
        trianglePath.close();
        canvas.drawPath(trianglePath, paint);
        //五角星
        Path fpStartPath = new Path();
        fpStartPath.moveTo(70, 450);
        fpStartPath.lineTo(130, 450);
        fpStartPath.lineTo(85, 480);
        fpStartPath.lineTo(100, 420);
        fpStartPath.lineTo(115, 480);
        fpStartPath.close();
        canvas.drawPath(fpStartPath, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        //圆
        canvas.drawCircle(300, 70, 50, paint);
        //矩形
        canvas.drawRect(250, 140, 350, 190, paint);
        //圆角矩形
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect(250, 210, 350, 260, 10, 10, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 250, 210, paint);
        }
        //椭圆
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawOval(250, 280, 350, 330, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 250, 280, paint);
        }
        //三角形
        Path trianglePath1 = new Path();
        trianglePath1.moveTo(300, 350);
        trianglePath1.lineTo(350, 400);
        trianglePath1.lineTo(250, 400);
        trianglePath1.close();
        canvas.drawPath(trianglePath1, paint);
        //五角星
        Path fpStartPath1 = new Path();
        fpStartPath1.moveTo(270, 450);
        fpStartPath1.lineTo(330, 450);
        fpStartPath1.lineTo(285, 480);
        fpStartPath1.lineTo(300, 420);
        fpStartPath1.lineTo(315, 480);
        fpStartPath1.close();
        canvas.drawPath(fpStartPath1, paint);

        //设置渐变器
        Shader shader = new LinearGradient(0, 0, 100, 100, new int[]{Color.RED, Color.YELLOW}, null, Shader.TileMode.REPEAT);
        paint.setShader(shader);
        //设置阴影
        paint.setShadowLayer(45, 10, 10, Color.GREEN);
        //圆
        canvas.drawCircle(500, 70, 50, paint);
        //矩形
        canvas.drawRect(450, 140, 550, 190, paint);
        //圆角矩形
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect(450, 210, 550, 260, 10, 10, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 450, 210, paint);
        }
        //椭圆
        if(Build.VERSION.SDK_INT >= 21) {
            canvas.drawOval(450, 280, 550, 330, paint);
        }else{
            canvas.drawText("API LEVEL低于21", 450, 280, paint);
        }
        //三角形
        Path trianglePath2 = new Path();
        trianglePath2.moveTo(500, 350);
        trianglePath2.lineTo(550, 400);
        trianglePath2.lineTo(450, 400);
        trianglePath2.close();
        canvas.drawPath(trianglePath2, paint);
        //五角星
        Path fpStartPath2 = new Path();
        fpStartPath2.moveTo(470, 450);
        fpStartPath2.lineTo(530, 450);
        fpStartPath2.lineTo(485, 480);
        fpStartPath2.lineTo(500, 420);
        fpStartPath2.lineTo(515, 480);
        fpStartPath2.close();
        canvas.drawPath(fpStartPath2, paint);
    }
}
