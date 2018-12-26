package com.linuxpara.mvp_originalcode.login.view.login;

import com.linuxpara.mvp_originalcode.login.LoginActivity;

import java.util.List;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public class LoginViewProxy extends ILoginView<LoginActivity> {

    public LoginViewProxy(LoginActivity view) {
        super(view);
    }

    @Override
    public void showLoginSuccess(List<String> data) {
        try {
            getView().showLoginSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoginFailed(Throwable e) {
        try {
            getView().showLoginFailed(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
