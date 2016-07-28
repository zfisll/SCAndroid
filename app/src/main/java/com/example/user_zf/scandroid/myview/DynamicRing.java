package com.example.user_zf.scandroid.myview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.user_zf.scandroid.R;

/**
 * Created by user_zf on 16/7/15.
 * 动态圆环
 */
public class DynamicRing extends View implements ValueAnimator.AnimatorUpdateListener{

    public final int DEFAULT_MIN_WIDTH = 600;
    public final float STATIC_RING_RADUIS_PERSENT = 0.91f;//静态圆环半径占最大半径百分比
    public final float STATIC_RING_WIDTH_PERSENT = 0.10f;//静态圆环半径占最大半径百分比
    public final float SHADOW_PERSENT = 0.03f;//周边阴影的宽度
    public final int DRAW_DURATION_TIME = 2000;//画图持续时间

    public final int GREEN = Color.argb(255, 81, 194, 155);
    public final int PURPLE = Color.argb(255, 126,121, 255);

    RingArc greenArc, purpleArc;

    float width;
    float height;
    float raduis;//最大半径

    //圆环内的内容
    String str1 = "15000.00";
    //两个数值，计算圆环的两种颜色的百分比
    float f1 = 300f;
    float f2 = 300f;
    //两种颜色各占得角度
    int angle1 = 0;
    int angle2 = 0;

    int[] gradientColors_1 = {
            GREEN,
            GREEN
    };
    int[] gradientColors_2 = {
            PURPLE,
            PURPLE
    };
    int[] shadowColors = {
            Color.argb(54, 136, 136, 136),
            Color.argb(1, 136, 136, 136)
    };

    Context context;

    public DynamicRing(Context context){
        super(context);
        this.context = context;
    }

    public DynamicRing(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Log.e("zf_tag", "onDraw greenXAngle="+greenArc.getxAngle()+" purpleXAngle="+purpleArc.getxAngle());
        Log.e("zf_tag", "onDraw greenAngle="+greenArc.getAngle()+" purpleAngle="+purpleArc.getAngle());
        this.invalidate();//重绘
    }

    /**
     * 设置数据，并根据数据画图
     * @param str1
     * @param f1
     * @param f2
     */
    public void setValue(String str1, float f1, float f2){
        Log.e("zf_tag", "setValue()");
        this.str1 = str1;
        this.f1 = f1;
        this.f2 = f2;
        //计算两条曲线的角度
        initAngle();
        resetParams();
        //定义两个RingArc对象
        greenArc = new RingArc();
        greenArc.setAngle(0);
        greenArc.setxAngle(angle1);
        Paint greenPaint = getPaint();
        greenPaint.setStyle(Paint.Style.STROKE);
        greenPaint.setStrokeWidth(raduis*STATIC_RING_WIDTH_PERSENT);
        greenPaint.setShader(new SweepGradient(0, 0, gradientColors_1, null));
        greenArc.setPaint(greenPaint);
        //紫色环曲线
        purpleArc = new RingArc();
        purpleArc.setAngle(angle1);
        purpleArc.setxAngle(angle2);
        Paint purplePaint = getPaint();
        purplePaint.setStyle(Paint.Style.STROKE);
        purplePaint.setStrokeWidth(raduis*STATIC_RING_WIDTH_PERSENT);
        purplePaint.setShader(new SweepGradient(0, 0, gradientColors_2, null));
        purpleArc.setPaint(purplePaint);
        //定义改变xAngle的属性动画
        final ValueAnimator greenAnimator = ObjectAnimator.ofInt(greenArc, "xAngle", 0, angle1);
        greenAnimator.setInterpolator(new AccelerateInterpolator());
        greenAnimator.setDuration(DRAW_DURATION_TIME);
        greenAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle = (int) animation.getAnimatedValue();
                greenArc.setxAngle(angle);
                invalidate();
            }
        });
        ValueAnimator purpleAnimator = ObjectAnimator.ofInt(purpleArc, "xAngle", 0, angle2);
        purpleAnimator.setInterpolator(new AccelerateInterpolator());
        purpleAnimator.setDuration(DRAW_DURATION_TIME);
        purpleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle = (int) animation.getAnimatedValue();
                purpleArc.setxAngle(angle);
                invalidate();
            }
        });
        //两个动画同时开始
        AnimatorSet togetherAnimator = new AnimatorSet();
        togetherAnimator.playTogether(greenAnimator, purpleAnimator);
        togetherAnimator.start();
    }

    /**
     * 增加f2，重绘图形
     */
    public void addF2(){
        //重绘图形
        setValue("1500.00", f1, f2);
    }

    /**
     * 自己测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    public int measure(int origin){
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specLength = MeasureSpec.getSize(origin);
        if(specMode == MeasureSpec.EXACTLY){
            return specLength;
        }else{
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specLength);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(greenArc!=null && purpleArc!=null) {
            canvas.translate(width / 2, height / 2);
            canvas.rotate(270, 0, 0);
            drawStaticRing(canvas);
            canvas.rotate(90, 0, 0);
            drawText(canvas);
        }
    }

    /**
     * 画静态渐变圆环
     * @param canvas
     */
    public void drawStaticRing(Canvas canvas){
        RectF rectF = new RectF(-raduis*STATIC_RING_RADUIS_PERSENT, -raduis*STATIC_RING_RADUIS_PERSENT, raduis*STATIC_RING_RADUIS_PERSENT, raduis*STATIC_RING_RADUIS_PERSENT);
        if(greenArc.getxAngle()==0 && purpleArc.getxAngle()==0){
            return;
        }
        Log.e("zf_tag", "greenAngle="+greenArc.getAngle()+"====greenXAngle="+greenArc.getxAngle());
        Log.e("zf_tag", "purpleAngle="+purpleArc.getAngle()+"====purpleXAngle="+purpleArc.getxAngle());
        if(greenArc.getxAngle() > 0){
            canvas.drawArc(rectF, greenArc.getAngle(), greenArc.getxAngle(), false, greenArc.getPaint());
        }
        if(purpleArc.getxAngle() > 0){
            canvas.drawArc(rectF, purpleArc.getAngle(), purpleArc.getxAngle(), false, purpleArc.getPaint());
        }
        //绘制两种颜色的半圆
        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);
        if(angle1 >= angle2) {//先画紫色的半圆，再画绿色的
            canvas.rotate(angle1, 0, 0);
            if (angle2 > 0) {
                paint.setColor(PURPLE);
                RectF pupleRect = new RectF(raduis * (STATIC_RING_RADUIS_PERSENT - STATIC_RING_WIDTH_PERSENT / 2), -raduis * STATIC_RING_WIDTH_PERSENT / 2, raduis * (STATIC_RING_RADUIS_PERSENT + STATIC_RING_WIDTH_PERSENT / 2), raduis * STATIC_RING_WIDTH_PERSENT / 2);
                canvas.drawArc(pupleRect, 175, 190, false, paint);
            }
            canvas.rotate(angle2, 0, 0);
            if (angle1 > 0) {
                paint.setColor(GREEN);
                RectF greenRect = new RectF(raduis * (STATIC_RING_RADUIS_PERSENT - STATIC_RING_WIDTH_PERSENT / 2), -raduis * STATIC_RING_WIDTH_PERSENT / 2, raduis * (STATIC_RING_RADUIS_PERSENT + STATIC_RING_WIDTH_PERSENT / 2), raduis * STATIC_RING_WIDTH_PERSENT / 2);
                canvas.drawArc(greenRect, 175, 190, false, paint);
            }
        }else{//先画绿色半圆，再画紫色的
            if (angle1 > 0) {
                paint.setColor(GREEN);
                RectF greenRect = new RectF(raduis * (STATIC_RING_RADUIS_PERSENT - STATIC_RING_WIDTH_PERSENT / 2), -raduis * STATIC_RING_WIDTH_PERSENT / 2, raduis * (STATIC_RING_RADUIS_PERSENT + STATIC_RING_WIDTH_PERSENT / 2), raduis * STATIC_RING_WIDTH_PERSENT / 2);
                canvas.drawArc(greenRect, 175, 190, false, paint);
            }
            canvas.rotate(angle1, 0, 0);
            if (angle2 > 0) {
                paint.setColor(PURPLE);
                RectF pupleRect = new RectF(raduis * (STATIC_RING_RADUIS_PERSENT - STATIC_RING_WIDTH_PERSENT / 2), -raduis * STATIC_RING_WIDTH_PERSENT / 2, raduis * (STATIC_RING_RADUIS_PERSENT + STATIC_RING_WIDTH_PERSENT / 2), raduis * STATIC_RING_WIDTH_PERSENT / 2);
                canvas.drawArc(pupleRect, 175, 190, false, paint);
            }
            canvas.rotate(angle2, 0, 0);
        }
    }

    /**
     * 绘制圆环周边的阴影，阴影是一种扩散的渐变
     * @param canvas
     */
    public void drawShadow(Canvas canvas){
        Paint paint = getPaint();
        float persent = STATIC_RING_RADUIS_PERSENT + STATIC_RING_WIDTH_PERSENT/2 + SHADOW_PERSENT/2;
        RectF rectF = new RectF(-raduis*persent, -raduis*persent, raduis*persent, raduis*persent);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(raduis*SHADOW_PERSENT);
        paint.setShader(new RadialGradient(0, 0, raduis*0.05f, shadowColors, null, Shader.TileMode.REPEAT));
        canvas.drawArc(rectF, 0, 360, false, paint);
    }

    /**
     * 画字
     * @param canvas
     */
    public void drawText(Canvas canvas){
        Paint paint = getPaint();
        paint.setColor(ContextCompat.getColor(context, R.color.ring_text));
        paint.setTextSize(context.getResources().getDimension(R.dimen.ring_big_size));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str1, 0, 50, paint);
    }

    /**
     * 重置view的宽高、圆环的半径
     */
    public void resetParams(){
        width = getWidth();
        height = getHeight();
        raduis = Math.min(width/2-4, height/2-4);
    }

    /**
     * 初始化画笔
     */
    public Paint getPaint(){
        Paint paint = new Paint();
        paint.reset();
        paint.setAntiAlias(true);
        return paint;
    }

    /**
     * 根据数据初始化角度
     */
    private void initAngle(){
        angle1 = (int) (f1/(f1+f2) * 360);
        angle2 = 360 - angle1;
    }

    /**
     * 根据长度和角度计算线段的起点和终点坐标
     * 此处解释一下：坐标系的方向是 x->右 y->下
     * @param angle
     * @param length
     * @return
     */
    private float[] calculatePoint(float angle, float length){
        //记录圆心的x,y值
        float[] points = new float[2];
        if(angle <= 90f){
            points[0] = (float) Math.sin(Math.PI * (angle+90)/180) * length;
            points[1] = - (float) Math.cos(Math.PI * (angle+90)/180) * length;
        }else if(angle>90f && angle<=180f){
            points[0] = (float) Math.cos(Math.PI * angle/180) * length;
            points[1] = (float) Math.sin(Math.PI * angle/180) * length;
        }else if(angle>180f && angle<=270f){
            points[0] = - (float) Math.sin(Math.PI * (angle-90)/180) * length;
            points[1] = (float) Math.cos(Math.PI * (angle-90)/180) * length;
        }else{
            points[0] = - (float) Math.cos(Math.PI * (angle-180)/180) * length;
            points[1] = - (float) Math.sin(Math.PI * (angle-180)/180) * length;
        }
        return points;
    }

    /**
     * 环曲线对象，属性动画修改该对象的属性
     */
    private class RingArc{
        Paint paint;
        int angle;//起始角度
        int xAngle;//整个曲线长度所占角度

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public int getxAngle() {
            return xAngle;
        }

        public void setxAngle(int xAngle) {
            this.xAngle = xAngle;
        }
    }

}
