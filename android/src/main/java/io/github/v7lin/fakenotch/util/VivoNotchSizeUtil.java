package io.github.v7lin.fakenotch.util;

import android.content.Context;
import android.util.TypedValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class VivoNotchSizeUtil {
    private VivoNotchSizeUtil() {
    }

    public static boolean hasNotchInScreen(Context context) {
        boolean hasNotch = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class FtFeature = cl.loadClass("android.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport");
            hasNotch = (boolean) get.invoke(FtFeature, new Object[]{0x00000020});
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (ClassNotFoundException e) {
        }
        return hasNotch;
    }

    public static int[] getNotchSize(Context context) {
        int width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics()));
        int height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, context.getResources().getDisplayMetrics()));
        return new int[]{width, height};
    }
}
