package com.tcl.smarthome.mvplibrary.presenter;

import com.tcl.smarthome.mvplibrary.model.HttpModel;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

/**
 * Description: 网络请求的Presenter层
 * Auther: 陈占洋
 * Data: 2017/3/15.
 */

public class HttpPresenter<M extends HttpModel, V extends IHttpView> extends Presenter<M,V> {

    public HttpPresenter(Class<M> clazz) {
        super(clazz);
    }

    public void fetchData(Object... params) {
        mModel.fetchData(params);
    }

    /**
     * 开始请求网络
     */
    public void onHttpStart() {
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpStart();
        }
    }

    /**
     * 请求网络成功
     */
    public void onHttpSuccess(Object bean) {
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpSuccess(bean);
        }
    }

    /**
     * 请求网络失败
     */
    public void onHttpFaild(Throwable t) {
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.get().onHttpFaild(t);
        }
    }
}
