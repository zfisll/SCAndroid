package com.example.user_zf.scandroid.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.mvp.bean.LoginBean;
import com.example.user_zf.scandroid.mvp.prasenter.LoginPrasenter;

/**
 * mvp模式的核心累，接收view抛出的业务需求，调用model来处理该业务需求
 */
public class LoginActivity extends Activity implements ILoginView, View.OnClickListener{

    EditText etUserName;
    EditText etPassword;
    Button bLogin;
    Button bClear;

    LoginPrasenter loginPrasenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPrasenter = new LoginPrasenter(this);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bClear = (Button) findViewById(R.id.bClear);
        bLogin.setOnClickListener(this);
        bClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:
                loginPrasenter.dealLogin();
                break;
            case R.id.bClear:
                loginPrasenter.dealClear();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        etUserName.setText("");
    }

    @Override
    public void clearPassword() {
        etPassword.setText("");
    }

    @Override
    public void showSuccessInfo(LoginBean bean) {
        Toast.makeText(this, "登录成功：username="+bean.getUserName()+" password="+bean.getPassword(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedInfo(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}
