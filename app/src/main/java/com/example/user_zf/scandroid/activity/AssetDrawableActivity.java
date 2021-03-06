package com.example.user_zf.scandroid.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.utils.NightModeHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析Asset文件夹下所有的图片
 */
public class AssetDrawableActivity extends AppCompatActivity {

    ImageView ivPicture;
    int curIndex = 0;//当前图片索引
    int allCount = 0;//assets文件夹下图片数量
    String[] pictures;//图片名称数组
    AssetManager manager;

    NightModeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new NightModeHelper(this, R.style.AppTheme);
        setContentView(R.layout.activity_asset_drawable);

        ivPicture = (ImageView) findViewById(R.id.ivPicture);
        initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this, SkinPreActivity.class));
            Toast.makeText(this, "拦截返回事件", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        return true;
    }

    /**
     * 获取assets下的图片信息
     */
    public void initData(){
        manager = this.getAssets();
        try {
            pictures = manager.list("");
            allCount = pictures.length;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 显示下一张图片，如果到了最后一张，从头开始显示
     * @param source
     */
    public void toNext(View source){
        if(curIndex >= pictures.length){
            curIndex = 0;
        }
        while(!pictures[curIndex].endsWith(".jpg") && !pictures[curIndex].endsWith(".jpeg") && !pictures[curIndex].endsWith(".gif") && !pictures[curIndex].endsWith(".png")){
            curIndex++;
            if(curIndex >= allCount){
                curIndex = 0;
            }
        }
        //先回首ImageView当前显示的图片
        BitmapDrawable bd = (BitmapDrawable) ivPicture.getDrawable();
        if(bd!=null && bd.getBitmap()!=null && !bd.getBitmap().isRecycled()){
            bd.getBitmap().recycle();
            Toast.makeText(this, "recycle old bitmap", Toast.LENGTH_SHORT).show();
        }
        //当前索引是一张图片，显示（根据path）
        try {
            InputStream is = manager.open(pictures[curIndex++]);
            ivPicture.setImageBitmap(BitmapFactory.decodeStream(is));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void changeSkin(View source){
        helper.toggle();
    }

    public void startActivity(View source){
        this.startActivity(new Intent(this, SkinActivity.class));
    }
}
