package com.linuxpara.edgemvp_complier.utils;

import java.util.Set;

import javax.lang.model.element.Modifier;

/**
 * Date: 2018/12/25
 * *************************************************************
 * Auther: 陈占洋
 * *************************************************************
 * Email: zhanyang.chen@gmail.com
 * *************************************************************
 * Description: 修饰符工具类
 */
public class ModifierUtil {
    /**
     * 判断修饰符是否有效。非static、非final、非natvie、非private 有效。
     *
     * @param modifiers 修饰符数组
     * @return 这组修饰符是否有效。true:有效  false:无效
     */
    public static boolean isValid(Set<Modifier> modifiers) {
        boolean valid = true;
        for (Modifier modifier : modifiers) {
            valid = !isStatic(modifier) &&
                    !isFinal(modifier) &&
                    !isNative(modifier) &&
                    !isPrivate(modifier);
        }
        return valid;
    }

    /**
     * 是否是static修饰符
     *
     * @param modifier 修饰符
     * @return true 是static false 不是static
     */
    private static boolean isStatic(Modifier modifier) {
        return modifier == Modifier.STATIC;
    }

    /**
     * 是否是final修饰符
     *
     * @param modifier 修饰符
     * @return true:修饰符是final，否则false。
     */
    private static boolean isFinal(Modifier modifier) {
        return modifier == Modifier.FINAL;
    }

    /**
     * 是否是native修饰符
     *
     * @param modifier 修饰符
     * @return true:修饰符是native，否则false。
     */
    private static boolean isNative(Modifier modifier) {
        return modifier == Modifier.NATIVE;
    }

    /**
     * 是否是private修饰符
     *
     * @param modifier 修饰符
     * @return true:修饰符是private，否则false。
     */
    private static boolean isPrivate(Modifier modifier) {
        return modifier == Modifier.PRIVATE;
    }
}
