package com.linuxpara.edgemvp;

import com.linuxpara.edgemvp_annotation.MvpPresenter;
import com.linuxpara.edgemvp_annotation.MvpView;
import com.linuxpara.edgemvp_annotation.proxy.EdgeMvpPresenter;
import com.linuxpara.edgemvp_annotation.proxy.EdgeMvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public class EdgeMvp {

    private static Map<String, WeakReference<EdgeMvpPresenter>> sCache = new HashMap<>();

    public static <P extends EdgeMvpPresenter> P createPresenter(Object view) {
        MvpView mvpViewAnnotation = view.getClass().getAnnotation(MvpView.class);
        if (mvpViewAnnotation == null) {
            throw new RuntimeException("view 未被MvpView注解修饰！");
        }

        String key = mvpViewAnnotation.key();
        Class<?> presClz = mvpViewAnnotation.presenter();

        try {
            Object presObj = presClz.newInstance();

            Class<?> presProxyClz = Class.forName(view.getClass().getPackage().getName() + ".presenter." + key.toLowerCase() + "." + key + "PresenterProxy");
            EdgeMvpPresenter presProxy = (EdgeMvpPresenter) presProxyClz.getConstructor(view.getClass(), presClz)
                    .newInstance(view, presObj);

            sCache.put(key, new WeakReference<EdgeMvpPresenter>(presProxy));

            return (P) presProxy;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <V extends EdgeMvpView> V getViewProxy(Object presenter) {
        MvpPresenter mvpPresenterAnnotation = presenter.getClass().getAnnotation(MvpPresenter.class);
        if (mvpPresenterAnnotation == null){
            throw new RuntimeException("presenter 未被MvpPresenter注解修饰！");
        }

        String key = mvpPresenterAnnotation.key();
        WeakReference<EdgeMvpPresenter> wrPresenter = sCache.get(key);
        if (wrPresenter != null && wrPresenter.get() != null){
            return (V) wrPresenter.get().getViewProxy();
        }

        return null;
    }
}
