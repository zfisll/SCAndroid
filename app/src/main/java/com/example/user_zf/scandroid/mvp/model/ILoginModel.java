package com.example.user_zf.scandroid.mvp.model;

/**
 * Created by user_zf on 16/7/16.
 */
public interface ILoginModel {
    public void login(String userName, String password, OnLoginListener listener);
}
