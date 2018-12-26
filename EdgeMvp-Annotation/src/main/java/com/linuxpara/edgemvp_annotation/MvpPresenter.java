package com.linuxpara.edgemvp_annotation;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:presenter的注解
 */
public @interface MvpPresenter {

    String key();

    Class<?> view();
}
