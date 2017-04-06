package com.tcl.smarthome.mvplibrary.model;

import com.tcl.smarthome.mvplibrary.presenter.Presenter;

/**
 * Description: MVP Model层的顶层 所有的P类需要继承此类
 * Auther: 陈占洋
 * Email: zhanyang.chen@gmail.com
 * Data: 2017/3/18.
 */

public class Model<P extends Presenter> {

    protected P mPresenter;

    public void bindPresenter(P p){
        mPresenter = p;
    }
}
