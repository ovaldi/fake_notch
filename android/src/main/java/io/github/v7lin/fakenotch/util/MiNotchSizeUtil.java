package io.github.v7lin.fakenotch.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MiNotchSizeUtil {
    private MiNotchSizeUtil() {
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static boolean hasNotch() {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method m = c.getDeclaredMethod("getInt", String.class, int.class);
            m.setAccessible(true);
            // SystemProperties.getInt(KEY_MIUI_NOTCH, 0)
            int property = (int) m.invoke(null, "ro.miui.notch", 0);
            return property == 1;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        int widthResId = context.getResources().getIdentifier("notch_width", "dimen", "android");
        int heightResId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (widthResId > 0 && heightResId > 0) {
            ret[0] = context.getResources().getDimensionPixelSize(widthResId);
            ret[1] = context.getResources().getDimensionPixelSize(heightResId);
        }
        return ret;
    }
}
