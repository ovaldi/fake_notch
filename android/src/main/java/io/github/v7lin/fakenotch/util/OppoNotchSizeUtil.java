package io.github.v7lin.fakenotch.util;

import android.content.Context;

public final class OppoNotchSizeUtil {
    private OppoNotchSizeUtil() {
    }

    public static boolean hasNotchInScreen(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static int[] getNotchSize(Context context) {
        return new int[]{324, 80};
    }
}
