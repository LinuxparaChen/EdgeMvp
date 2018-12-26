package com.linuxpara.edgemvp_complier.utils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * Date: 2018/12/24
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public class MessagerUtil {

    private static Messager mMessager;

    /**
     * 初始化
     *
     * @param messager
     */
    public static void init(Messager messager) {
        mMessager = messager;
    }

    /**
     * 输出错误信息，并终止apt编译
     *
     * @param s
     * @param args
     */
    public static void error(String s, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(s, args));
    }

    /**
     * 输出警告信息
     *
     * @param s
     * @param args
     */
    public static void warning(String s, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.WARNING, String.format(s, args));
    }

    /**
     * 提示信息
     *
     * @param s
     * @param args
     */
    public static void note(String s, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(s, args));
    }
}
