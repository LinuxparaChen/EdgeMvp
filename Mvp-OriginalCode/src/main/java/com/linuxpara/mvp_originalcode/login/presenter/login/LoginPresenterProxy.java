package com.linuxpara.mvp_originalcode.login.presenter.login;


import com.linuxpara.mvp_originalcode.login.LoginActivity;
import com.linuxpara.mvp_originalcode.login.LoginPresenter;
import com.linuxpara.mvp_originalcode.login.view.login.EdgeMvpView;
import com.linuxpara.mvp_originalcode.login.view.login.LoginViewProxy;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public class LoginPresenterProxy extends ILoginPresenter<LoginActivity,LoginPresenter> {

    public LoginPresenterProxy(LoginActivity view, LoginPresenter presenter) {
        super(view, presenter);
    }

    @Override
    public void login(String name, String passwd) {
        try {
            getPresenter().login(name,passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected EdgeMvpView<LoginActivity> createViewProxy(LoginActivity view) {
        return new LoginViewProxy(view);
    }
}
