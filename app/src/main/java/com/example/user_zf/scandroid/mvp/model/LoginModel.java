package com.example.user_zf.scandroid.mvp.model;

import android.text.TextUtils;

import com.example.user_zf.scandroid.mvp.bean.LoginBean;

/**
 * Created by user_zf on 16/7/16.
 */
public class LoginModel implements ILoginModel{
    @Override
    public void login(String userName, String password, OnLoginListener listener) {
        if(TextUtils.isEmpty(userName)){
            listener.onFailed("用户名不能为空");
        }else if(TextUtils.isEmpty(password)){
            listener.onFailed("密码不能为空");
        }else{
            if(userName.equals("zuofeng") && password.equals("zfisll")){
                LoginBean bean = new LoginBean();
                bean.setPassword(password);
                bean.setUserName(userName);
                listener.onSuccess(bean);
            }else{
                listener.onFailed("用户名密码不匹配");
            }
        }
    }
}
