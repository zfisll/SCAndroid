package com.example.user_zf.scandroid.mvp.prasenter;

import com.example.user_zf.scandroid.mvp.bean.LoginBean;
import com.example.user_zf.scandroid.mvp.model.ILoginModel;
import com.example.user_zf.scandroid.mvp.model.LoginModel;
import com.example.user_zf.scandroid.mvp.model.OnLoginListener;
import com.example.user_zf.scandroid.mvp.view.ILoginView;

/**
 * Created by user_zf on 16/7/16.
 * 登录的主导器
 */
public class LoginPrasenter {

    ILoginModel model;
    ILoginView view;

    public LoginPrasenter(ILoginView view, ILoginModel model){
        this.model = model;
        this.view = view;
    }

    public LoginPrasenter(ILoginView view){
        this.view = view;
        model = new LoginModel();
    }

    /**
     * 处理view的登录操作
     */
    public void dealLogin(){
        model.login(view.getUserName(), view.getPassword(), new OnLoginListener() {
            @Override
            public void onFailed(String errMsg) {
                view.showFailedInfo(errMsg);
            }

            @Override
            public void onSuccess(LoginBean user) {
                view.showSuccessInfo(user);
            }
        });
    }

    /**
     * 处理view的清空操作
     */
    public void dealClear(){
        view.clearUserName();
        view.clearPassword();
    }

}
