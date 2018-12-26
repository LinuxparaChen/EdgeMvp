package com.linuxpara.edgemvp_annotation;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:view层的注解
 */
public @interface MvpView {

    String key();

    Class<?> presenter();
}
