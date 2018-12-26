package com.linuxpara.mvp_originalcode.login.presenter.login;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public abstract class ILoginPresenter<V,P> extends EdgeMvpPresenter<V,P>{

    public ILoginPresenter(V view, P presenter) {
        super(view, presenter);
    }

    public abstract void login(final String name, final String passwd);
}
