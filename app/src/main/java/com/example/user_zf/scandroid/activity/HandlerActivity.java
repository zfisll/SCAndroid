package com.example.user_zf.scandroid.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.utils.SDUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 本demo介绍handler的使用，在ui线程 和 其他线程中分别的使用
 * handler的用法：
 * 1、其他线程发送消息
 * 2、主线程处理消息
 */
public class HandlerActivity extends AppCompatActivity {

    ImageView ivWall;

    final String PATH = "/androidesk/wallpapers/";
    String[] images = {"image_1.jpg", "image_2.jpg", "image_3.jpg", "image_4.jpg", "image_5.jpg"};

    final int MsgFlag = 0x123;

    int index = 0;//当前显示照片索引

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //2、处理消息
            if(msg.what == MsgFlag){
                String imagePath = SDUtils.getSdCardPath() + PATH + images[index];
                Log.d("zf_tag", "处理消息：" + imagePath);
                index = ++index%5;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(imagePath, options);
                ivWall.setImageBitmap(bm);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        ivWall = (ImageView) findViewById(R.id.ivWall);
        //定时器2秒发送一次消息
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("zf_tag", "发送消息");
                handler.sendEmptyMessage(MsgFlag);
            }
        }, 0, 5000);
    }
}
