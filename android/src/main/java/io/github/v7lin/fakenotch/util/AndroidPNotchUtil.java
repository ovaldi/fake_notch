package io.github.v7lin.fakenotch.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

@TargetApi(Build.VERSION_CODES.P)
public class AndroidPNotchUtil {
    private AndroidPNotchUtil() {
    }

    public static boolean hasNotch(Window window) {
        View decorView = window.getDecorView();
        WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
        if (rootWindowInsets != null) {
            DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
            return displayCutout != null && displayCutout.getBoundingRects() != null;
        }
        return false;
    }
}
