package com.tcl.smarthome.mvpapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tcl.smarthome.mvplibrary.model.HttpModel;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

import java.lang.reflect.Type;

/**
 * Description:
 * Auther: 陈占洋
 * Email: zhanyang.chen@gmail.com
 * Data: 2017/3/18.
 */

public class BaseHttpActivity<M extends HttpModel,B extends Object> extends AppCompatActivity implements IHttpView<B> {

    private M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        Log.i("chen_zhanyang", "init: "+genericSuperclass);
        Type[] genericInterfaces = this.getClass().getGenericInterfaces();
        Log.i("chen_zhanyang", "init: "+genericInterfaces);
    }

    @Override
    public void onHttpStart() {

    }

    @Override
    public void onHttpSuccess(B b) {

    }

    @Override
    public void onHttpFaild(Throwable t) {

    }
}
