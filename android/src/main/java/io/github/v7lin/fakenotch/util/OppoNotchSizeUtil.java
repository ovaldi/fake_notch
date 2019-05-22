package io.github.v7lin.fakenotch.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.O)
public final class OppoNotchSizeUtil {
    private OppoNotchSizeUtil() {
    }

    public static boolean hasNotch(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static int[] getNotchSize() {
        return new int[]{324, 80};
    }
}
