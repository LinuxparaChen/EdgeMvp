package com.linuxpara.mvp_originalcode.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.linuxpara.edgemvp_annotation.ExtractItf;
import com.linuxpara.edgemvp_annotation.MvpView;
import com.linuxpara.mvp_originalcode.R;
import com.linuxpara.mvp_originalcode.login.presenter.login.ILoginPresenter;

import java.util.List;


/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description: 登录的view层
 */
@MvpView(key = "Login",presenter = LoginPresenter.class)
public class LoginActivity extends AppCompatActivity {

    private ILoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = EdgeMvp.createPresenter(this);
        mPresenter.login("","");
    }

    @ExtractItf
    public void showLoginSuccess(List<String> data){
        Toast.makeText(this,data.get(0),Toast.LENGTH_SHORT).show();
    }

    @ExtractItf
    public void showLoginFailed(Throwable e){
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
    }


}
