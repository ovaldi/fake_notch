package io.github.v7lin.fakenotch.util;

import android.content.Context;

import java.lang.reflect.Method;

public final class HwNotchSizeUtil {

    private HwNotchSizeUtil() {
    }

    /**
     * 是否是刘海屏手机：
     * true：是刘海屏；false：非刘海屏。
     */
    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 获取刘海尺寸：width、height
     * int[0]值为刘海宽度 int[1]值为刘海高度。
     */
    public static int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (Exception e) {
        }
        return ret;
    }
}
