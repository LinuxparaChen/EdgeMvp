package com.linuxpara.mvp_originalcode.login.view.login;

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
public abstract class ILoginView<V> extends EdgeMvpView<V> {

    public ILoginView(V view) {
        super(view);
    }

    public abstract void showLoginSuccess(List<String> data);

    public abstract void showLoginFailed(Throwable e);
}
