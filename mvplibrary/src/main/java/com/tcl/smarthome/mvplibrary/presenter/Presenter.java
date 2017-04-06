package com.tcl.smarthome.mvplibrary.presenter;

import com.tcl.smarthome.mvplibrary.model.Model;
import com.tcl.smarthome.mvplibrary.view.IView;

import java.lang.ref.WeakReference;

/**
 * Description: MVP Presenter层的顶层 所有的P类需要继承此类
 * Auther: 陈占洋
 * Data: 2017/3/15.
 */

public class Presenter<M extends Model,V extends IView> {

    protected WeakReference<V> mViewRef;
    protected M mModel;

    public Presenter(Class<M> clazz){
        try {
            mModel = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        mModel.bindPresenter(this);
    }

    /**
     * 依赖到视图层 视图层初始化的时候需要调用
     * @param v
     */
    public void attach(V v){
        mViewRef = new WeakReference<>(v);

    }

    /**
     * 从视图层解除依赖 在视图层销毁的时候调用 否则那个容易内存泄漏
     */
    public void dettach(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
