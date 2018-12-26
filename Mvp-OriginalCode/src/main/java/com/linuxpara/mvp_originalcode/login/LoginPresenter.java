package com.linuxpara.mvp_originalcode.login;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.linuxpara.edgemvp_annotation.ExtractItf;
import com.linuxpara.edgemvp_annotation.MvpPresenter;
import com.linuxpara.mvp_originalcode.login.view.login.ILoginView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:登录的presenter层
 */
@MvpPresenter(key = "Login",view = LoginActivity.class)
public class LoginPresenter {

    public static class LoginHandler extends Handler {

        private final WeakReference<LoginPresenter> mWRPresenter;

        public LoginHandler(LoginPresenter presenter) {
            mWRPresenter = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mWRPresenter == null || mWRPresenter.get() == null) {
                return;
            }
            if (msg.what == 0) {
                Throwable e = new Throwable((String) msg.obj);
                ILoginView viewProxy = (ILoginView) EdgeMvp.getViewProxy(mWRPresenter.get());
                viewProxy.showLoginFailed(e);
            } else {
                ArrayList<String> data = new ArrayList<>();
                data.add((String) msg.obj);
                ILoginView viewProxy = (ILoginView) EdgeMvp.getViewProxy(mWRPresenter.get());
                viewProxy.showLoginSuccess(data);
            }
        }
    }

    private static Handler sHander;

    public LoginPresenter() {
        sHander = new LoginHandler(this);
    }


    /**
     * 登录
     *
     * @param name
     * @param passwd
     */
    @ExtractItf
    public void login(final String name, final String passwd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = Message.obtain();
                    if (validate(name, passwd)) {
                        msg.what = 1;
                        msg.obj = "登录成功\n用户名：" + name + " 密码：" + passwd;
                    } else {
                        msg.what = 0;
                        msg.obj = "登录失败";
                    }
                    sHander.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 验证用户名，密码
     *
     * @return
     */
    private boolean validate(String name, String passwd) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(passwd)) {
            return false;
        }

        if ("陈占洋".equals(name) && "123456".equals(passwd)) {
            return true;
        }

        return false;
    }
}
