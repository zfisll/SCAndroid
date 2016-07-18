package com.example.user_zf.scandroid.mvp.view;

import com.example.user_zf.scandroid.mvp.bean.LoginBean;

/**
 * Created by user_zf on 16/7/16.
 * 登录View接口
 */
public interface ILoginView {
    String getUserName();
    String getPassword();
    void clearUserName();
    void clearPassword();
    void showSuccessInfo(LoginBean bean);
    void showFailedInfo(String errMsg);
}
