package io.github.v7lin.fakenotch;

import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;

import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.github.v7lin.fakenotch.util.HwNotchSizeUtil;
import io.github.v7lin.fakenotch.util.MiNotchSizeUtil;
import io.github.v7lin.fakenotch.util.OppoNotchSizeUtil;
import io.github.v7lin.fakenotch.util.VivoNotchSizeUtil;
import manufacturer.android.Manufacturer;

/**
 * FakeNotchPlugin
 */
public class FakeNotchPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "v7lin.github.io/fake_notch");
        channel.setMethodCallHandler(new FakeNotchPlugin(registrar));
    }

    private static final String METHOD_HASNOTCHINSCREEN = "hasNotchInScreen";

    private final Registrar registrar;

    public FakeNotchPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (TextUtils.equals(METHOD_HASNOTCHINSCREEN, call.method)) {
            hasNotchInScreen(call, result);
        } else {
            result.notImplemented();
        }
    }

    private void hasNotchInScreen(MethodCall call, Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            View decorView = registrar.activity().getWindow().getDecorView();
            DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
            List<Rect> boundingRects = displayCutout.getBoundingRects();
            result.success(boundingRects != null);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            Manufacturer manufacturer = Manufacturer.recognizer();
            if (manufacturer == Manufacturer.HUAWEI) {
                result.success(HwNotchSizeUtil.hasNotchInScreen(registrar.context()));
            } else if (manufacturer == Manufacturer.XIAOMI) {
                result.success(MiNotchSizeUtil.hasNotchInScreen());
            } else if (manufacturer == Manufacturer.OPPO) {
                result.success(OppoNotchSizeUtil.hasNotchInScreen(registrar.context()));
            } else if (manufacturer == Manufacturer.VIVO) {
                result.success(VivoNotchSizeUtil.hasNotchInScreen(registrar.context()));
            } else {
                result.success(false);
            }
        } else {
            result.success(false);
        }
    }
}
