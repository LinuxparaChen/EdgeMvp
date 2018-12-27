package com.linuxpara.edgemvp_example.login;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.linuxpara.edgemvp_annotation.ExtractItf;
import com.linuxpara.edgemvp_annotation.MvpPresenter;
import com.linuxpara.edgemvp.EdgeMvp;
import com.linuxpara.edgemvp_example.login.view.login.ILoginView;

/**
 * Date: 2018/12/26
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
@MvpPresenter(key = "Login",view = LoginActivity.class)
public class LoginPresenter {

    private static class LoginHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {

        }
    }

    private static LoginHandler sHandler;

    public LoginPresenter(){
        sHandler = new LoginHandler();
    }

    @ExtractItf
    public void login(final String name, final String passwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                int ret = (int) (Math.random() * 10 % 2);
                if (ret == 0){
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((ILoginView) EdgeMvp.getViewProxy(LoginPresenter.this))
                                    .showLoginFailed(new Exception("登录失败！"));
                        }
                    });
                }else {
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((ILoginView) EdgeMvp.getViewProxy(LoginPresenter.this))
                                    .showLoginSuccess("登录成功: name = "+name+", passwd = "+passwd);
                        }
                    });
                }
            }
        }).start();
    }
}
