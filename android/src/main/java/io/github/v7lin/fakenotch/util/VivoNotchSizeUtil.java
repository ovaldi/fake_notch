package io.github.v7lin.fakenotch.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class VivoNotchSizeUtil {
    public static final int NOTCH_IN_SCREEN_VOIO_MARK = 0x00000020;//是否有凹槽
    public static final int ROUNDED_IN_SCREEN_VOIO_MARK = 0x00000008;//是否有圆角

    private VivoNotchSizeUtil() {
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static boolean hasNotch(Context context) {
        boolean hasNotch = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class FtFeature = cl.loadClass("android.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport", int.class);
            hasNotch = (boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO_MARK);
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (ClassNotFoundException e) {
        }
        return hasNotch;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static int[] getNotchSize(Context context) {
        int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics()));
        int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, context.getResources().getDisplayMetrics()));
        return new int[]{width, height};
    }
}
