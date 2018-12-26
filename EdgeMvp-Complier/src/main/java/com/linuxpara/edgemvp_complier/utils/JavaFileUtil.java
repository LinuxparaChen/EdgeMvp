package com.linuxpara.edgemvp_complier.utils;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;

/**
 * Date: 2018/12/26
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description:
 */
public class JavaFileUtil {

    /**
     * 生成java文件
     *
     * @param pkg  包名
     * @param spec java文件描述
     * @param filer
     * @return
     */
    public static void generateJavaFile(String pkg, TypeSpec spec, Filer filer) {

        JavaFile javaFile = JavaFile.builder(pkg, spec).build();

        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
            MessagerUtil.error("生成 %s 文件失败!\n %s", spec.name, e.toString());
        }
    }
}
