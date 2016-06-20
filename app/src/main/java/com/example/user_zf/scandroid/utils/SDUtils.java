package com.example.user_zf.scandroid.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by user_zf on 16/6/20.
 */
public class SDUtils {

    /**
     * 获取手机sd卡路径
     * @return
     */
    public static String getSdCardPath(){
        //判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdCardExist){
            //获取根目录
            File sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }

}
