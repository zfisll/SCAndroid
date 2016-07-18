package com.example.user_zf.scandroid.mvp.model;

import com.example.user_zf.scandroid.mvp.bean.LoginBean;

/**
 * Created by user_zf on 16/7/16.
 */
public interface OnLoginListener {
    public void onFailed(String errMsg);
    public void onSuccess(LoginBean user);
}
