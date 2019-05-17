package io.github.v7lin.fakenotch.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public final class OppoNotchSizeUtil {
    private OppoNotchSizeUtil() {
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static boolean hasNotch(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static int[] getNotchSize() {
        return new int[]{324, 80};
    }
}
