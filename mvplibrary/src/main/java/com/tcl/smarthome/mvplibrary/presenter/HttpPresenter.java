package com.tcl.smarthome.mvplibrary.presenter;

import android.util.Log;

import com.tcl.smarthome.mvplibrary.model.HttpModel;
import com.tcl.smarthome.mvplibrary.model.Model;
import com.tcl.smarthome.mvplibrary.model.bean.Bean;
import com.tcl.smarthome.mvplibrary.view.IHttpView;
import com.tcl.smarthome.mvplibrary.view.IView;

/**
 * Description: 网络请求的Presenter层
 * Auther: 陈占洋
 * Data: 2017/3/15.
 */

public class HttpPresenter<M extends HttpModel,B extends Bean,V extends IHttpView<B>> extends Presenter<M,V> {

    private static final String TAG = "HttpPresenter";

    public HttpPresenter(Class<M> clazz) {
        super(clazz);
    }

    /**
     * 开始请求网络
     */
    public void onHttpStart() {
        Log.i(TAG, "onHttpStart: ");
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpStart();
        }
    }

    /**
     * 请求网络成功
     */
    public void onHttpSuccess(B bean) {
        Log.i(TAG, "onHttpSuccess: "+bean.toString());
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpSuccess(bean);
        }
    }

    /**
     * 请求网络失败
     */
    public void onHttpFaild(Throwable e) {
        Log.i(TAG, "onHttpFaild: ");
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpFaild(e);
        }
    }
}
