package com.tcl.smarthome.mvplibrary.view;

import com.tcl.smarthome.mvplibrary.model.bean.Bean;

/**
 * Description: 网络请求的View层
 * Auther: 陈占洋
 * Data: 2017/3/15.
 */

public interface IHttpView<B extends Bean> extends IView {
    /**
     * 做开始请求网络的界面处理
     */
    void onHttpStart();

    /**
     * 做请求网络成功的界面处理
     */
    void onHttpSuccess(B b);

    /**
     * 做请求网络失败的界面处理
     */
    void onHttpFaild(Throwable t);
}
