package com.example.user_zf.scandroid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.user_zf.scandroid.myview.MoveBall;
import com.example.user_zf.scandroid.utils.NavigationBarUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 控制MoveBall的移动
 */
public class MoveBallActivity extends AppCompatActivity {

    MoveBall ball;
    //小球移动速度
    int speedX = 2;
    int speedY = 3;
    //小球球心和半径
    int centerX = 40;
    int centerY = 40;
    int radius = 40;
    //屏幕宽高
    int screenWidth;
    int screenHeight;

    //重绘小球
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x123) {
                ball.setCenter(centerX, centerY);
                ball.invalidate();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ball = new MoveBall(this);
        setContentView(ball);
        //获取屏幕宽和高
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenHeight = metrics.heightPixels- NavigationBarUtils.getNavigationBarHeight(this);
        Toast.makeText(this, "虚拟按键高度："+ NavigationBarUtils.getNavigationBarHeight(this), Toast.LENGTH_SHORT).show();
        screenWidth = metrics.widthPixels;
        //设置一个定时器周期改变小球位置
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int left = centerX - radius;
                int right = centerX + radius;
                int top = centerY - radius;
                int bottom = centerY + radius;
                if(right >= screenWidth){
                    speedX *= -1;
                    centerX = screenWidth-(right-screenWidth)-radius;
                }
                if(left <= 0){
                    speedX *= -1;
                    centerX = radius - left;
                }
                if(bottom >= screenHeight){
                    speedY *= -1;
                    centerY = screenHeight-(bottom-screenHeight)-radius;
                }
                if(top <= 0){
                    speedY *= -1;
                    centerY = radius - top;
                }
                handler.sendEmptyMessage(0x123);
                //改变小球位置
                centerX += speedX;
                centerY += speedY;
            }
        }, 0, 10);
    }

}
