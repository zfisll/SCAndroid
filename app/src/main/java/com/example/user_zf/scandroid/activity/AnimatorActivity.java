package com.example.user_zf.scandroid.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.myview.ShapeHolder;

import java.util.ArrayList;

/**
 * 属性动画demo
 */
public class AnimatorActivity extends AppCompatActivity {

    static final float BALL_SIZE = 50f;//小球半径
    static final int FULL_TIME = 2000;//下落总时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        LinearLayout llContainer = (LinearLayout)findViewById(R.id.llContainer);
        llContainer.addView(new MyAnimatorView(this));
    }

    /**
     * 自定义属性动画View
     */
    public class MyAnimatorView extends View implements ValueAnimator.AnimatorUpdateListener{
        public final ArrayList<ShapeHolder> balls = new ArrayList<>();
        public MyAnimatorView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //只处理按下和移动事件
            int action = event.getAction();
            if(action!=MotionEvent.ACTION_DOWN && action!=MotionEvent.ACTION_MOVE){
                return false;
            }
            ShapeHolder newBall = addBall(event.getX(), event.getY());
            //计算小球的动画时间
            float startY = event.getY();
            float endY = getHeight() - BALL_SIZE*2;
            if(startY >= endY){
                return false;
            }
            int duration = (int) ((endY - startY)/getHeight() * FULL_TIME);
            //定义属性动画  1、小球下落
            ValueAnimator fallAnimator = ObjectAnimator.ofFloat(newBall, "y", startY, endY);
            fallAnimator.setDuration(duration);
            fallAnimator.setInterpolator(new AccelerateInterpolator());
            fallAnimator.addUpdateListener(this);
            //定义小球落地时  2、变形动画  a.宽度加倍 b.高度减半 c.y降低半个球的高度
            ValueAnimator widthAnimator = ObjectAnimator.ofFloat(newBall, "width", newBall.getWidth(), newBall.getWidth()+BALL_SIZE*2);
            widthAnimator.setDuration(duration/8);
            widthAnimator.setRepeatCount(1);
            widthAnimator.setRepeatMode(ValueAnimator.REVERSE);
            widthAnimator.setInterpolator(new DecelerateInterpolator());
            widthAnimator.addUpdateListener(this);
            ValueAnimator heightAnimator = ObjectAnimator.ofFloat(newBall, "height", newBall.getHeight(), newBall.getHeight()-BALL_SIZE);
            heightAnimator.setDuration(duration/8);
            heightAnimator.setRepeatCount(1);
            heightAnimator.setRepeatMode(ValueAnimator.REVERSE);//反向重复一次
            heightAnimator.setInterpolator(new DecelerateInterpolator());
            ValueAnimator yOffsetAnimator = ObjectAnimator.ofFloat(newBall, "y", endY, endY+BALL_SIZE);
            yOffsetAnimator.setDuration(duration/8);
            yOffsetAnimator.setRepeatCount(1);
            yOffsetAnimator.setRepeatMode(ValueAnimator.REVERSE);//反向重复一次
            yOffsetAnimator.setInterpolator(new DecelerateInterpolator());
            //定义属性动画 3、地面弹起动画
            ValueAnimator bounceAnimator = ObjectAnimator.ofFloat(newBall, "y", endY, endY-(endY-startY)/2);
            bounceAnimator.setDuration(duration/2);
            bounceAnimator.setInterpolator(new DecelerateInterpolator());
            bounceAnimator.addUpdateListener(this);
            //定义属性动画  3、小球消失
            ValueAnimator fadeAnimator = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
            fadeAnimator.setDuration(250);
            fadeAnimator.setInterpolator(new LinearInterpolator());
            //定义变化过程的监听事件
            fadeAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //移除小球
                    balls.remove(((ObjectAnimator) animation).getTarget());
                }
            });
            //定义组合动画组合 下落 消失两个动画
            AnimatorSet secondeSet = new AnimatorSet();
            secondeSet.playTogether(widthAnimator, heightAnimator, yOffsetAnimator);
            AnimatorSet thirdSet = new AnimatorSet();
            thirdSet.playTogether(bounceAnimator, fadeAnimator);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(fallAnimator, secondeSet, thirdSet);
            animatorSet.start();//开始执行动画
            return true;
        }

        //添加一个小球，并加入balls列表
        private ShapeHolder addBall(float x, float y){
            //设置形状
            OvalShape circle = new OvalShape();
            circle.resize(BALL_SIZE*2, BALL_SIZE*2);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shape = new ShapeHolder(drawable);
            //设置位置
            shape.setX(x);
            shape.setY(y);
            //设置颜色
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            //将red blue green 组合成ARGB颜色
            int color = 0xff000000 + red<<16|green<<8|blue;
            int darkColor = 0xff000000 | red/4<<16 | green/4<<8 | blue/4;
            Paint paint = drawable.getPaint();
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f, BALL_SIZE,color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            shape.setPaint(paint);
            balls.add(shape);
            return shape;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //绘制balls中的ShapeHolder对象
            for(ShapeHolder shapeHolder : balls){
                canvas.save();
                canvas.translate(shapeHolder.getX(), shapeHolder.getY());
                shapeHolder.getShape().draw(canvas);
                canvas.restore();
            }
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            this.invalidate();//属性改变的时候重绘界面
        }
    }
}
