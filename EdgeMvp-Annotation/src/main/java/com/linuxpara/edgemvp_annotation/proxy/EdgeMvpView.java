package com.linuxpara.edgemvp_annotation.proxy;

import java.lang.ref.WeakReference;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:view代理类，的顶级父类
 */
public abstract class EdgeMvpView<V> {

    private final WeakReference<V> mWRView;

    public EdgeMvpView(V view){
        mWRView = new WeakReference<>(view);
    }

    protected V getView() throws Exception {
        if (mWRView !=null && mWRView.get() != null){
            return mWRView.get();
        }
        throw new Exception("view 引用已经被GC回收！");
    }

}
